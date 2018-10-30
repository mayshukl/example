package org.unnati.example.transactionmanager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class TxnManager {

    public static TxnManager getTxnManager() {
        return txnManager;
    }

    public static final TxnManager txnManager=new TxnManager();


    private EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("EmbeddedExample");
    EntityManager entityManager=entityManagerFactory.createEntityManager();


    public  <E> void save(E entity){
        entityManager.getTransaction().begin();

        entityManager.persist(entity);

        entityManager.getTransaction().commit();

    }

    public  <E> List<E> getAll(String className,Class E){
       Query query= entityManager.createQuery("from "+className);
        List<E> result=query.getResultList();
        return result;
    }



}