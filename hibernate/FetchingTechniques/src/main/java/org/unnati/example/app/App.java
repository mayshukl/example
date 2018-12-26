package org.unnati.example.app;
import org.hibernate.Session;
import org.junit.Assert;
import org.unnati.example.dao.HibernateDaoSupport;
import org.unnati.example.entities.*;

import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    private HibernateDaoSupport dao=new HibernateDaoSupport();
    PersistenceUtil persistenceUtil=Persistence.getPersistenceUtil();
    private long counter=0;

    public static void main(String[] args){
        App app=new App();
        app.runApp();

        app.validateElementCollectionLoad();
        app.validateOneToManyLoad();
        app.validateGetReference();
        app.validateFetchMode();
        app.validateFetchProfiles();
    }

    public void runApp(){
        System.out.println("Running Application");
        this.createUser();
        this.createUser();
        this.createItems();
        this.createMaterialReceipt(dao.getById("UserProfile",1));
        this.createMaterialReceipt(dao.getById("UserProfile",1));
        this.createPurchaseOrder((UserProfile)dao.getById("UserProfile",1));
        this.createPurchaseOrder((UserProfile)dao.getById("UserProfile",1));
        this.listPurchaseOrders();
        this.modifyPurchaseOrder(dao.getById("PurchaseOrder",1),dao.getById("UserProfile",2));
    }


    public void createUser(){
       UserProfile userProfile=new UserProfile();
       userProfile.setName("User"+this.counter++);
       dao.save(userProfile);
       this.registerAsCustomer(userProfile);
    }

    public void registerAsCustomer(UserProfile userProfile){
        CustomerProfile customerProfile=new CustomerProfile();
        customerProfile.setUserProfile(userProfile);
        dao.save(customerProfile);
    }

    public void createItems(){
        Item item1=new Item();
        item1.setName("Item1");

        ItemAttribute attribute1=new ItemAttribute();
        attribute1.setAttributeName("Attribute1");
        attribute1.setAttributeValue("Value1");
        item1.getItemAttributes().put("Attribute1",attribute1);

        ItemAttribute attribute2=new ItemAttribute();
        attribute2.setAttributeName("Attribute2");
        attribute2.setAttributeValue("Value1");
        item1.getItemAttributes().put("Attribute2",attribute2);
        dao.save(item1);

        Item item2=new Item();
        item2.setName("Item2");
        dao.save(item2);
    }

    public void createMaterialReceipt(UserProfile userProfile){
        MaterialReceipt mr=new MaterialReceipt();
        mr.setMrNo("MRNo"+this.counter++);
        mr.setAddedBy(userProfile);
        mr.setModifiedBy(userProfile);
        dao.save(mr);
        List<Item> items=dao.getAll("Item");
        for(Item item:items){
            MaterialReceiptItem materialReceiptItem=new MaterialReceiptItem(item,mr,userProfile);
            dao.save(materialReceiptItem);
            mr.getItems().add(materialReceiptItem);
        }

        dao.save(mr);
    }

    public void createPurchaseOrder(UserProfile user){
        PurchaseOrder po=new PurchaseOrder();
        po.setPurchaseOrderNo("PO"+counter++);
        po.setAddedBy(user);
        po.setModifiedBy(user);
        dao.save(po);
        List<Item> items=dao.getAll("Item");
        for(Item item:items){
            PurchaseOrderItems poItem=new PurchaseOrderItems(po,item);
            po.getItemsBag().add(poItem);
            dao.save(poItem);
        }
        dao.save(po);
    }

    public void modifyPurchaseOrder(PurchaseOrder po,UserProfile user){
        po.setModifiedBy(user);
        dao.update(po);
    }

    public void listPurchaseOrders(){
        dao.getEntityManager().clear();
        List<PurchaseOrder> purchaseOrders=dao.getAll("PurchaseOrder");
    }

    public void validateGetReference(){
        Item item=dao.getReference(Item.class,new Integer(1));
        Assert.assertFalse(persistenceUtil.isLoaded(item));

    }

    public void validateElementCollectionLoad(){
        dao.getEntityManager().clear();

        Item item=dao.getById("Item",1);
        Assert.assertTrue("Validating ItemAttribute Proxy",item.getItemAttributes() instanceof org.hibernate.collection.internal.PersistentMap);

        Assert.assertFalse(persistenceUtil.isLoaded(item.getItemAttributes()));
    }

    public void validateOneToManyLoad(){
        dao.getEntityManager().clear();

        List<PurchaseOrder> purchaseOrders=dao.getAll("PurchaseOrder");

        Assert.assertTrue(purchaseOrders.size()==2);

        for(PurchaseOrder po:purchaseOrders){
            Assert.assertTrue(po.getItemsBag().size()>0);
        }

    }

    public void validateFetchMode(){
        dao.getEntityManager().clear();
        System.out.println("====================================Testing the fetch mode=================================");
        MaterialReceipt materialReceipt =dao.getById(MaterialReceipt.class,1);
        Assert.assertNotNull(materialReceipt);
        System.out.println("====================================Testing the fetch mode with subselect=================================");
        dao.getEntityManager().clear();
        List<Item> items =dao.getAll("Item");
        Assert.assertNotNull(items);
        int count=0;
        for(Item item:items) {
            count++;
            Assert.assertTrue(persistenceUtil.isLoaded(item.getItemAttributes().size()));
        }
        System.out.println("Total count : "+count);
    }

    public void validateFetchProfiles(){
        dao.getEntityManager().clear();
        System.out.println("====================================Testing the fetch Profiles=================================");

        dao.getEntityManager().unwrap(Session.class).enableFetchProfile("PurchaseOrderFetchProfile");

        Map<String,Object> properties=new HashMap<>();
        properties.put("javax.persistence.fetchgraph",dao.getEntityManager().getEntityGraph("PurchaseOrderGraph"));

        PurchaseOrder purchaseOrder=dao.getById(PurchaseOrder.class,1,properties);

    }
}