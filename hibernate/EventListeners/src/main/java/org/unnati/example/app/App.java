package org.unnati.example.app;

import org.junit.Assert;
import org.unnati.example.dao.HibernateDaoSupport;
import org.unnati.example.entities.Item;

public class App {

    HibernateDaoSupport dao=new HibernateDaoSupport();

    public static void main(String[] args) {
        App app=new App();
        app.runApp();
    }


    public void runApp(){
        System.out.println("Running App");
        this.createItem();
        this.reloadItem();
        this.updateItem();
        this.removeItem();
        dao.getEntityManager().close();
    }

    public void createItem(){
        Item item=new Item();
        item.setName("Item1");
        dao.getEntityManager().getTransaction().begin();
        dao.getEntityManager().persist(item);
        dao.getEntityManager().getTransaction().commit();
    }

    public void reloadItem(){
        Item item=dao.getById("Item",1);
        dao.getEntityManager().clear();
        Assert.assertFalse(dao.getEntityManager().contains(item));
        item=dao.getById("Item",1);
        Assert.assertTrue(dao.getEntityManager().contains(item));
    }

    public void updateItem(){
        Item item=dao.getById("Item",1);
        dao.getEntityManager().clear();
        item.setName("Item2");
        dao.getEntityManager().getTransaction().begin();
        dao.getEntityManager().merge(item);
        dao.getEntityManager().getTransaction().commit();
    }

    public void removeItem(){
        Item item=dao.getById("Item",1);
        dao.getEntityManager().getTransaction().begin();
        dao.getEntityManager().remove(item);
        dao.getEntityManager().getTransaction().commit();
    }

}
