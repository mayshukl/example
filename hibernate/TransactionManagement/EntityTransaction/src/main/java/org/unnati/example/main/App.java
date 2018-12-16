package org.unnati.example.main;


import org.unnati.example.dao.DaoSupport;
import org.unnati.example.entities.Item;
import org.unnati.example.entities.PurchaseOrder;
import org.unnati.example.entities.PurchaseOrderItem;

import javax.persistence.EntityManager;
import java.util.List;

public class App {

    private DaoSupport dao=new DaoSupport("EntityTransactionExample");

    public static void main(String[] args){
        App app=new App();
        app.runApp();
    }

    public void runApp(){
        System.out.println("Running Application");
      //  this.createItem();
        this.createPurchaseOrder();
    }

    public void createItem(){
        Item item1 =new Item();
        item1.setName("Item1");
        dao.save(item1);
        Item item2 =new Item();
        item2.setName("Item1");
        dao.save(item2);
        item1.setName("ModifiedItem");
        dao.save(item1);

       // dao.deleteAllEntities("Item");
      //  dao.getSingleResult("Item",123);
    }

    public void createPurchaseOrder(){
        Item item1 =new Item();
        item1.setName("Item1");
        dao.save(item1);
        Item item2 =new Item();
        item2.setName("Item2");
        dao.save(item2);

        PurchaseOrder po=new PurchaseOrder();
    //    po.getItems().add(item1);
    //    po.getItems().add(item2);
        dao.save(po);

        PurchaseOrderItem purchaseOrderItem1=new PurchaseOrderItem(po.getId(),item1.getId());
        purchaseOrderItem1.setPrice(100);
        PurchaseOrderItem purchaseOrderItem2=new PurchaseOrderItem(po.getId(),item2.getId());
        purchaseOrderItem2.setPrice(100);
        po.getItems().add(purchaseOrderItem1);
        po.getItems().add(purchaseOrderItem2);
        dao.save(purchaseOrderItem1);
        dao.save(purchaseOrderItem2);

       dao.createEntityManager().getTransaction().begin();
       // List<PurchaseOrderItem> items=dao.executeQueryOptimistic("from PurchaseOrderItem where poId=?1",new Object[]{po.getId()});
        List<PurchaseOrderItem> items=dao.executeQueryPessimistic("from PurchaseOrderItem where poId=?1",new Object[]{po.getId()});
        Double totalPrice=0.0;

        for(PurchaseOrderItem item:items){
            totalPrice=totalPrice+item.getPrice();
        }
        po.setTotalAmount(totalPrice);
        dao.createEntityManager().persist(po);
        dao.createEntityManager().flush();
        dao.createEntityManager().getTransaction().commit();

    }

}
