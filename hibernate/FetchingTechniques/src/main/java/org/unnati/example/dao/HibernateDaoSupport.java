package org.unnati.example.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class HibernateDaoSupport {

    private static  EntityManagerFactory emf= Persistence.createEntityManagerFactory("FetchTechnique");

    private  ThreadLocal<EntityManager> threadLocal=new ThreadLocal<>();

    public static void close(){
        emf.close();
    }

    public  EntityManager getEntityManager(){
        EntityManager entityManager=threadLocal.get();
        if(entityManager==null) {
            entityManager = emf.createEntityManager();
            threadLocal.set(entityManager);
        }
        return entityManager;
    }

    public <E> E  getReference(Class className,int id){
       return (E) this.getEntityManager().getReference(className,id);
    }

    public <E> void  save(E entity){
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().persist(entity);
        this.getEntityManager().getTransaction().commit();
    }
    public <E> void  update(E entity){
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().merge(entity);
        this.getEntityManager().getTransaction().commit();
    }



    public <E> List<E> getAll(String className){
        Query query= this.getEntityManager().createQuery("from "+className);
        List<E> result=query.getResultList();
        return result;
    }

    public <E> E getById(Class className, int id, Map properties){
        return (E) this.getEntityManager().find(className,id,properties);
    }
    public <E> E getById(Class className,int id){
        return (E) this.getEntityManager().find(className,id);
    }

    public <E> E getById(String className,long id){
        Query query= this.getEntityManager().createQuery("from "+className+" where id="+id);
        List<E> result=query.getResultList();
        return result.size()>0 ? result.get(0) : null;
    }

    public <E> void delete(E entity){
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().remove(entity);
        this.getEntityManager().getTransaction().commit();
    }

}