package org.unnati.example.main;

import org.unnati.example.dao.HibernateDaoSupport;
import org.unnati.example.entities.Category;
import org.unnati.example.entities.Item;
import org.unnati.example.entities.PurchaseOrder;
import org.unnati.example.entities.PurchaseOrderItem;

public class App {

    private HibernateDaoSupport dao=new HibernateDaoSupport();
    private long counter=0;

    public static void main(String[] args){
        App app=new App();
        app.runApp();
    }

    public void runApp(){
        System.out.println("Running Application");
       // this.registerItem();
       // this.registerCategory();
        this.createPurchaseOrder();
    }

    public void registerItem(){
        Category category=new Category();
        category.setName("Category"+counter++);

        Item item=new Item();
        item.setName("Item"+counter++);
        item.getCategories().add(category);
        dao.save(item);
    }

    public void registerCategory(){
        Category category=new Category();
        category.setName("Category"+counter++);

        Item item=new Item();
        item.setName("Item"+counter++);

        category.getItems().add(item);

        dao.save(category);

        System.out.println("Category Name"+item.getCategories().size());
    }

    public void createPurchaseOrder(){

        Item item1=new Item();
        item1.setName("Item"+counter++);


        Item item2=new Item();
        item2.setName("Item"+counter++);


        PurchaseOrder po=new PurchaseOrder();

        po.setOrderNo("PO"+counter++);

        dao.save(item1);
        dao.save(item2);
        dao.save(po);

        PurchaseOrderItem itemLink1=new PurchaseOrderItem("User1",po,item1);
        PurchaseOrderItem itemLink2=new PurchaseOrderItem("User1",po,item2);

     //   po.getItems().add(itemLink1);
     //   po.getItems().add(itemLink2);


        dao.save(itemLink1);
        dao.save(itemLink2);

    }
}
