package org.unnati.example.app;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditQuery;
import org.junit.Assert;
import org.unnati.example.entities.Item;
import org.unnati.example.entities.PurchaseOrder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class App {

    EntityManager entityManager= Persistence.createEntityManagerFactory("AuditLog").createEntityManager();

    public static void main(String args[]){
        App app=new App();
        app.runApp();
    }

    public void runApp(){
        System.out.println("Running Application");
       // this.createItem();
        this.createPurchaseOrder();
    }

    public void createItem(){
        Item item=new Item();
        item.setName("Item1");
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
    }

    public void createPurchaseOrder(){
        PurchaseOrder po=new PurchaseOrder();
        po.setPoName("PO1");
        Item item=new Item();
        item.setName("Item1ForPO");
        po.getItems().add(item);
        entityManager.getTransaction().begin();
        entityManager.persist(po);
        entityManager.getTransaction().commit();

        //Update Item

        item.setName("Item1ForPOUpdated");
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();

        Assert.assertTrue(((Item)po.getItems().toArray()[0]).getName().equals("Item1ForPOUpdated"));


        // Get Historical Data

        AuditReader auditReader= AuditReaderFactory.get(entityManager);
        AuditQuery query=auditReader.createQuery().forRevisionsOfEntity(PurchaseOrder.class,false,false);

        List<Object[]> purchaseOrderList=query.getResultList();

        for(Object[] tuple :purchaseOrderList){
            PurchaseOrder purchaseOrder=(PurchaseOrder)tuple[0];
            DefaultRevisionEntity revisionEntity=(DefaultRevisionEntity)tuple[1];
            RevisionType revisionType=(RevisionType) tuple[2];
            Assert.assertTrue(((Item)purchaseOrder.getItems().toArray()[0]).getName().equals("Item1ForPO"));

        }


        // update purchase order
        Item item2=new Item();
        item2.setName("Item2ForPO");
        po.getItems().add(item2);
        entityManager.getTransaction().begin();
        entityManager.persist(po);
        entityManager.getTransaction().commit();

    }
}
