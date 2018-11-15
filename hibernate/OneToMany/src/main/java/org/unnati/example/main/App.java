package org.unnati.example.main;

import org.unnati.example.dao.HibernateDaoSupport;
import org.unnati.example.entities.Item;
import org.unnati.example.entities.ItemAttribute;

import java.util.List;

public class App {

    private HibernateDaoSupport dao=new HibernateDaoSupport();


    public static void main(String[] args){
        App app=new App();
        app.runApp();
    }

    public void runApp(){
        System.out.println("Running Application");
        this.createItem();
        //dao.getEntityManager().clear();
        this.createItem();
        System.out.println("Getting all Objects");
        dao.getEntityManager().clear();
        this.getItems();
        dao.getEntityManager().clear();
        this.removeItems(this.getItem(0));
    }

    public void createItem(){
        Item item=new Item();
        item.setName("Item"+Math.random());

        ItemAttribute itemAttribute = new ItemAttribute();
        itemAttribute.setName("ItemAttribute"+Math.random());

        item.getItemAttributes().add(itemAttribute);
        itemAttribute.setItem(item);

        dao.save(item);

    }

    public void getItems(){
       List<Item> items= dao.getAll("Item");
       System.out.println("Before Accessing Attributes");
        items.get(0).getItemAttributes().get(0);
       System.out.println("Item Attribute Name "+items.get(0).getItemAttributes().get(0).getName());
    }

    public Item getItem(int index){
        List<Item> items= dao.getAll("Item");
        return items.get(index);
    }

    public  void removeItems(Item item){
        dao.delete(item);
    }
}
