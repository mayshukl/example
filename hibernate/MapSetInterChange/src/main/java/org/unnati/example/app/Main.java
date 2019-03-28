package org.unnati.example.app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static EntityManagerFactory emf= Persistence.createEntityManagerFactory("MapSetInterChange");

    public static void main(String[] args){
        System.out.println("Started");
    }
}
