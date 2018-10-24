package org.unnati.example.main;

import org.unnati.example.entity.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClass {
    private EntityManagerFactory entityManagerFactory= Persistence.createEntityManagerFactory("hello-word-persistence-unit");
    EntityManager entityManager=entityManagerFactory.createEntityManager();

    private long counter=1;

    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");
        MainClass mainClass=new MainClass();
        mainClass.saveMessage("Mayank");
        System.out.println("data saved");

    }

    private void saveMessage(String name) throws IOException {

        entityManager.getTransaction().begin();
        Message message=new Message();
        message.setId(counter++);
        message.setMessage("Hibernate world is welcoming you :"+name);


        entityManager.persist(message);
        entityManager.getTransaction().commit();


    }
}
