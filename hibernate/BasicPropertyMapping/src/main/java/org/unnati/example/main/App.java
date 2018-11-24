package org.unnati.example.main;


import org.unnati.example.dao.HibernateDaoSupport;
import org.unnati.example.entities.Bid;
import org.unnati.example.entities.Item;

public class App {

    private HibernateDaoSupport dao=new HibernateDaoSupport();
    private long counter=0;

    public static void main(String[] args){
        App app=new App();
        app.runApp();
    }

    public void runApp(){
        System.out.println("Running Application");
      //    this.createItem();
      //  this.createBid();
    }

    public void createItem(){
        Item item =new Item();
        item.setName("Item1");
        item.setPrice(10.00D);
        dao.save(item);
        System.out.println("Item Short Name :"+item.getShortName());
        dao.getEntityManager().clear();
        item=dao.getById("Item",item.getId());
        System.out.println("Item Short Name :"+item.getShortName());
        System.out.println("Item Type :"+item.getItemType());
    }

    public void createBid(){
        Bid bid=new Bid();
        dao.save(bid);

        System.out.println("Creation Date "+bid.getCreatedOn());
        System.out.println("Update Date "+bid.getUpdatedOn());
        System.out.println("Initial bid Amount "+bid.getInitialBidAmount());
    }
}
