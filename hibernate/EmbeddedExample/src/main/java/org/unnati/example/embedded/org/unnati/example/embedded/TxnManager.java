package org.unnati.example.embedded.org.unnati.example.embedded;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TxnManager {



    private EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("EmbeddedExample");
    EntityManager entityManager=entityManagerFactory.createEntityManager();


    public  <E> void save(E entity){
       entityManager.getTransaction().begin();

       entityManager.persist(entity);

        entityManager.getTransaction().commit();

   }



}
