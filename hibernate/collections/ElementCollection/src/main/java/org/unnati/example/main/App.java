package org.unnati.example.main;

import org.unnati.example.entities.Item;
import org.unnati.example.entities.ItemPrimaryDetail;
import org.unnati.example.transactionmanager.TxnManager;


import java.util.*;

public class App {

    public static void main(String[] agrs){
        System.out.println("Running Application");
        App app=new App();
        app.runApp();
        app.deleteData();
     //   app.printData();
    }

    public void runApp(){

        Item item=new Item();
        ItemPrimaryDetail itemPrimaryDetail=new ItemPrimaryDetail();
        item.setName("Item1");
        item.setItemPrimaryDetail(itemPrimaryDetail);

        itemPrimaryDetail.getItemAuditLog().add("Item Name has been changed");

        itemPrimaryDetail.getItemAlishNames().add("Itemfirst");
        itemPrimaryDetail.getItemAuditLog().add("Alish has been changed");

        itemPrimaryDetail.getItemAlishNames().add("Item-I");
        itemPrimaryDetail.getItemAuditLog().add("Alish has been changed");

        itemPrimaryDetail.getItemNoOfItemSold().put(new Date(),10);
        itemPrimaryDetail.getItemAuditLog().add(10+"Items has been sold on "+new Date());
        itemPrimaryDetail.getItemNoOfItemSold().put(new Date((Calendar.getInstance()).getTimeInMillis()+86400000),20);
        itemPrimaryDetail.getItemAuditLog().add(20+"Items has been sold on "+new Date((Calendar.getInstance()).getTimeInMillis()+86400000));

        itemPrimaryDetail.getItemImageName().add("Image1");
        itemPrimaryDetail.getItemAuditLog().add("Image Added :"+"Image1");

        itemPrimaryDetail.getItemImageName().add("Image2");
        itemPrimaryDetail.getItemAuditLog().add("Image Added :"+"Image2");


        TxnManager.getTxnManager().save(item);


    }


    public void deleteData(){
        List<Item> items=  TxnManager.getTxnManager().getAll("Item",Item.class);
        items.get(0).getItemPrimaryDetail().getItemAuditLog().clear();
      // delete from item sold
        List<Date> dates=new ArrayList<>();
        for(Map.Entry entry:items.get(0).getItemPrimaryDetail().getItemNoOfItemSold().entrySet()) {
            dates.add((Date)entry.getKey());
        }
        items.get(0).getItemPrimaryDetail().getItemNoOfItemSold().remove(dates.get(0));


        TxnManager.getTxnManager().save(items.get(0));

    }


    public void printData(){
       List<Item> items=  TxnManager.getTxnManager().getAll("Item",Item.class);

       for (Item item :items){
           ItemPrimaryDetail primaryDetail=item.getItemPrimaryDetail();
           System.out.format("%10s%20s", "id", "name");
           System.out.println();
           System.out.format("%10s%20s", item.getId(), item.getName());
           System.out.println();
           System.out.format("%20s","AlishName");
           System.out.println();
           for(String alishName:primaryDetail.getItemAlishNames()) {
               System.out.format("%20s",alishName);
               System.out.println();
           }
           System.out.println();
           System.out.format("%30s", "LogMessage");
           System.out.println();
           for(String auditLog:primaryDetail.getItemAuditLog()) {
               System.out.format("%30s", auditLog);
               System.out.println();
           }

           System.out.println();
           System.out.format("%20s", "ImageName");
           System.out.println();
           for(String imageName:primaryDetail.getItemImageName()) {
               System.out.format("%20s", imageName);
               System.out.println();
           }

           System.out.println();
           System.out.format("%20s%10s", "date","ItemSold");
           System.out.println();
           for(Map.Entry entry:primaryDetail.getItemNoOfItemSold().entrySet()) {
               System.out.format("%20s%10s", entry.getKey(),entry.getValue());
               System.out.println();
           }

           System.out.println();
       }

    }
}
