package org.unnati.example.dao;

import org.unnati.example.dao.exceptionhandler.HibernateExceptionHandler;

import javax.persistence.*;
import java.util.List;

public class DaoSupport {

    EntityManagerFactory emf;

    ThreadLocal<EntityManager> threadLocalEntityManager=new ThreadLocal<>();

    public DaoSupport(String persistentUnit){
        emf= Persistence.createEntityManagerFactory(persistentUnit);
    }

    public EntityManager createEntityManager(){
        EntityManager entityManager=threadLocalEntityManager.get();
            if(entityManager==null){
                entityManager=emf.createEntityManager();
                threadLocalEntityManager.set(entityManager);
            }
        return entityManager;
    }

    public void closeEntityManager(){
        EntityManager entityManager=threadLocalEntityManager.get();
        if(entityManager!=null){
            threadLocalEntityManager.remove();
        }
    }

    public <E> void save(E entity) {
        EntityManager entityManager = this.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        try {
            txn.begin();
            entityManager.persist(entity);
            txn.commit();
        }catch(PersistenceException ex){
            this.processException(ex);
            this.rollbackTransaction(txn);
            throw ex;
        }catch (Exception ex) {
            this.rollbackTransaction(txn);
            throw new RuntimeException(ex);
        }
    }



    public boolean deleteAllEntities(String entityName){
        Boolean result=true;

        EntityManager entityManager = this.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        try {
            txn.begin();
            Query query= entityManager.createQuery("delete from "+entityName);
            query.executeUpdate();
            txn.commit();
        }catch(PersistenceException ex){
            this.processException(ex);
            this.rollbackTransaction(txn);
            throw ex;
        }catch (Exception ex) {
            this.rollbackTransaction(txn);
            throw new RuntimeException(ex);
        }
        return result;
    }


    public <E> E getSingleResult(String entityName,Object id){
        EntityManager entityManager = this.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        try {
            Query query= entityManager.createQuery(" from "+entityName+" where id="+id.toString());
           // Query query= entityManager.createQuery(" from "+entityName);
            return (E)query.getSingleResult();
        }catch(PersistenceException ex){
            this.processException(ex);
            throw ex;
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public <E> List<E> executeQuery(String query){
        EntityManager entityManager = this.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        try {
            Query queryObject= entityManager.createQuery(query);
            return queryObject.getResultList();
        }catch(PersistenceException ex){
            this.processException(ex);
            throw ex;
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public <E> List<E> executeQueryOptimistic(String query,Object[] params){
        EntityManager entityManager = this.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        try {
        //    txn.begin();
            Query queryObject= entityManager.createQuery(query).setLockMode(LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            for(int i=0;i< (params==null?0:params.length);i++) {
                queryObject.setParameter(i+1, params[i]);
            }
            List<E> result=queryObject.getResultList();
        //    txn.commit();
            return result;
        }catch(PersistenceException ex){
            this.processException(ex);
            throw ex;
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public <E> List<E> executeQueryPessimistic(String query,Object[] params){
        EntityManager entityManager = this.createEntityManager();
        EntityTransaction txn = entityManager.getTransaction();
        try {
            //    txn.begin();
            Query queryObject= entityManager.createQuery(query).setLockMode(LockModeType.PESSIMISTIC_WRITE);
            for(int i=0;i< (params==null?0:params.length);i++) {
                queryObject.setParameter(i+1, params[i]);
            }
            List<E> result=queryObject.getResultList();
            //    txn.commit();
            return result;
        }catch(PersistenceException ex){
            this.processException(ex);
            throw ex;
        }catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void processException(PersistenceException persistentException){
        try{
            throw persistentException;
        }catch(RollbackException ex){
            HibernateExceptionHandler.handleRollbackException(ex);
        }catch(NoResultException ex){
            System.out.println("No Result found for query");
        }catch(NonUniqueResultException ex){
            System.out.println("More than one result found");
        }catch(OptimisticLockException ex){
            System.out.println("OptimisticLockException: Row was updated or deleted by another transaction.");
            HibernateExceptionHandler.handlePersistenceException(ex);
        }catch(PersistenceException ex){
            HibernateExceptionHandler.handlePersistenceException(ex);
        }
    }

    public void rollbackTransaction(EntityTransaction txn){
        try {
            txn.rollback();
        } catch (Exception exception) {
            System.out.println("Error while doing rollback");
            exception.printStackTrace();
        }
    }




}
