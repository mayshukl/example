package org.unnati.example.main;


import org.junit.Assert;
import org.unnati.example.entities.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    private static EntityManagerFactory emf= Persistence.createEntityManagerFactory("EntityManagerAPIExample");
    private long counter=0;

    public static void main(String[] args){
        App app=new App();
        app.runApp();
    }

    public void runApp(){
        System.out.println("Running Application");
        this.removeWithRollbackIdentifier();
        // this.createAndMerge();
        // this.createAndRefresh();
        //this.createAndDetach();
        //  this.createAndRemove();
       // this.createItem();
    }


    public void createItem(){
        Item item = new Item();
        item.setName("Item1");

        EntityManager entityManager=emf.createEntityManager();
        entityManager.getTransaction().begin();
        System.out.println("Id for the Item is "+item.getId());
        entityManager.persist(item);
        System.out.println("Id for the Item is "+item.getId());
        System.out.println("Name for the Item is "+item.getName());
        item.setName("Item2");
        System.out.println("Name for the Item is "+item.getName());
        entityManager.getTransaction().commit();
        System.out.println("Name for the Item is "+item.getName());


        //Find By Id
        entityManager.clear();
        item=entityManager.find(Item.class,item.getId());

        //getReference
        entityManager.clear();
        item=entityManager.getReference(Item.class,item.getId());
        System.out.println("after getting refrence");
        item.getName();
    }

    public void createAndRemove(){
        Item item = new Item();
        item.setName("Item1");
        EntityManager entityManager=emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Assert.assertFalse(entityManager.contains(item));
        entityManager.getTransaction().begin();
        /**
         *You need to merge detached entity before removing
         **/
        item=entityManager.merge(item);
        entityManager.remove(item);
        entityManager.getTransaction().commit();
    }

    public void createAndDetach(){
        Item item = new Item();
        item.setName("Item1");
        EntityManager entityManager=emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.detach(item);

        Assert.assertFalse(entityManager.contains(item));
    }

    public void createAndRefresh(){
        Item item = new Item();
        item.setName("Item1");
        EntityManager entityManager=emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        String newName="Item2";
        item.setName(newName);
        Assert.assertTrue(newName.equals(item.getName()));
        entityManager.refresh(item);
        Assert.assertFalse(newName.equals(item.getName()));
    }

    public void createAndMerge(){
        Item item = new Item();
        item.setName("Item1");
        EntityManager entityManager=emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        String newName="Item2";
        item.setName(newName);
        Assert.assertTrue(newName.equals(item.getName()));
        entityManager.merge(item);
        Assert.assertTrue(newName.equals(item.getName()));
    }

    public void removeWithRollbackIdentifier(){
        Item item = new Item();
        item.setName("Item1");
        EntityManager entityManager=emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
        entityManager.clear();

        Assert.assertFalse(entityManager.contains(item));
        entityManager.getTransaction().begin();
        /**
         *You need to merge detached entity before removing
         **/
        item=entityManager.merge(item);
        System.out.println("Item id :"+item.getId());
        entityManager.remove(item);
        System.out.println("Item id :"+item.getId());
     //   Assert.assertNotNull(item.getId());
     //   Assert.assertNull(item.getId());
        entityManager.getTransaction().commit();
    }

}
