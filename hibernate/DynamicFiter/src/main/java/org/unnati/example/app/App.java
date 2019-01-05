package org.unnati.example.app;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.junit.Assert;
import org.unnati.example.entities.Item;
import org.unnati.example.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUtil;
import javax.persistence.Query;

public class App {

    EntityManager entityManager= Persistence.createEntityManagerFactory("DynamicFilter").createEntityManager();

    public static void main(String args[]){
        App app=new App();
        app.runApp();
    }

    public void runApp(){
        System.out.println("Running Application");
        org.hibernate.Filter filter=entityManager.unwrap(Session.class).enableFilter("LimitByRank");
        filter.setParameter("currentUserRank",1);

        this.getAllItem();
        this.getByItemId();
        this.getAllUser();
        this.insertAndGetById();
    }

    public void getAllItem(){
        Query query=entityManager.createQuery(" from Item");
        query.getResultList();
    }

    public void getByItemId(){
        entityManager.find(Item.class,1);
    }



    public void getAllUser(){
        Query query=entityManager.createQuery(" from User");
        query.getResultList();
    }

    public void insertAndGetById(){
        User user=new User();
        user.setName("User1");
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.clear();

        user=entityManager.find(User.class,user.getId());
        PersistenceUtil persistenceUtil = Persistence.getPersistenceUtil();
      //  Hibernate.initialize(user.getItems());
        user.getItems().size();
    }

}
