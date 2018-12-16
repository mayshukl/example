package org.unnati.example.dao;

import org.unnati.example.dao.exceptionhandler.HibernateExceptionHandler;
import org.unnati.example.dao.jta.BitronixTransactionManagerSetUp;

import javax.persistence.*;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

public class DaoSupport {

    List<EntityManagerFactory> emfList=new ArrayList<>();

    ThreadLocal<List<EntityManager>> threadLocalEntityManager=new ThreadLocal<>();



    public DaoSupport(){
        try {
            BitronixTransactionManagerSetUp.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        emfList.add(Persistence.createEntityManagerFactory("Unit1"));
        emfList.add(Persistence.createEntityManagerFactory("Unit2"));


    }

    public List<EntityManager> createEntityManagers(){
        List<EntityManager> entityManagers=threadLocalEntityManager.get();
            if(entityManagers==null){
                entityManagers=new ArrayList<>();
                entityManagers.add(emfList.get(0).createEntityManager());
                entityManagers.add(emfList.get(1).createEntityManager());
                threadLocalEntityManager.set(entityManagers);
            }
        return entityManagers;
    }

    public void closeEntityManager(){
        List<EntityManager> entityManagers=threadLocalEntityManager.get();
        if(entityManagers!=null){
            threadLocalEntityManager.remove();
            entityManagers.get(0).close();
            entityManagers.get(1).close();
        }
    }

    public <E> void save(E entity) {
        List<EntityManager> entityManagers = this.createEntityManagers();
        UserTransaction txn= BitronixTransactionManagerSetUp.createUserTransaction();
        try {
            txn.begin();
            entityManagers.get(0).joinTransaction();
            entityManagers.get(1).joinTransaction();
            entityManagers.get(0).merge(entity);
            entityManagers.get(1).merge(entity);
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

    public void rollbackTransaction(UserTransaction txn){
        try {
            txn.rollback();
        } catch (Exception exception) {
            System.out.println("Error while doing rollback");
            exception.printStackTrace();
        }
    }


    public <E> List<E> getAllFromUnit1(String entity){
            EntityManager entityManager=threadLocalEntityManager.get().get(1);
            return entityManager.createQuery("from "+entity).getResultList();
    }

}
