package org.unnati.example.main;

import org.unnati.example.dao.HibernateDaoSupport;
import org.unnati.example.entities.User;

public class App {

    private HibernateDaoSupport dao=new HibernateDaoSupport();
    private long counter=0;

    public static void main(String[] args){
        App app=new App();
        app.runApp();
    }

    public void runApp(){
        System.out.println("Running Application");
        this.createUser();

    }

    public void createUser(){
        User user1=new User();
        user1.setName("User1");
        User user2=new User();
        user2.setName("User1");
        dao.getEntityManager().getTransaction().begin();
        dao.getEntityManager().persist(user1);
        dao.getEntityManager().persist(user2);
        dao.getEntityManager().getTransaction().commit();

        System.out.println("User1 id is "+user1.getId());
        System.out.println("User2 id is "+user2.getId());

    }

}
