package org.unnati.example.main;

import org.unnati.example.dao.HibernateDaoSupport;
import org.unnati.example.entities.*;

import java.util.List;

public class App {
    private HibernateDaoSupport dao=new HibernateDaoSupport();
    private long counter=0;

    public static void main(String[] args){
        App app=new App();
        app.runApp();
    }

    public void runApp(){
        System.out.println("Running Application");
        this.createItemTypes();
        this.dao.getEntityManager().clear();
        this.createItemWithType(1);
        this.dao.getEntityManager().clear();
        this.createItemWithType(1);
        this.dao.getEntityManager().clear();
        this.getItem();
    }

    private void createItemTypes(){
        ItemType type1 = new ItemType();
        type1.setItemType("SalesItem");
        ItemType type2 = new ItemType();
        type2.setItemType("PurchaseItem");

        dao.save(type1);
        dao.save(type2);
    }

    private void createItemWithType(int type){
        Item item=new Item();
        item.setName("Item"+counter++);
        item.setItemType(dao.getById("ItemType",type));

        ItemDetails itemDetails=new ItemDetails();
        itemDetails.setPurchasePrice(2);
        itemDetails.setSalesPrice(2);

        itemDetails.setItem(item);

       // this should be commented if we we do not define id generation logic in item detail entity
        item.setItemDetail(itemDetails);
        this.setWareHouse(item);
        dao.save(item);
        // next 2 lines are required if we do not define id generation logic in item detail entity
//        itemDetails.setId(item.getId());
//        dao.save(itemDetails);
    }

    private void setWareHouse(Item item){
        Address address=new Address();
        address.setFlat("12");
        address.setBuilding("SBA");
        address.setSector("23");
        address.setCity("ABC");
        address.setState("XYZ");
        address.setCountry("NY");

        Warehouse warehouse=new Warehouse();
        warehouse.setName("Warehouse1");
        warehouse.setAddress(address);

        item.setWarehouse(warehouse);

    }

    private void getItem(){
       List<Item> items=dao.getAll("Item");
        System.out.println("=========================Fetch Item====================================");
        Item item=items.get(0);
        System.out.println("=========================Fetch Item Type Lazy====================================");
        System.out.println("Item type "+item.getItemType().getId());
        item.getItemType().getItemType();
        System.out.println("==========================Fetch Item Detail ===================================");
        ItemDetails itemDetails=item.getItemDetail();
        int id=itemDetails.getId();
        dao.getEntityManager().clear();
        itemDetails=dao.getById("ItemDetails",id);
        System.out.println("==========================Fetch User from item Details=======================");
        item=itemDetails.getItem();
        item.getName();
        System.out.println("=============================Fetch Warehouse Details================================");
         item.getWarehouse().getName();
        System.out.println("=============================Fetch Address Details================================");
        item.getWarehouse().getAddress().getFlat();
    }


}
