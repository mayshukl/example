package org.unnati.example.main;


import org.unnati.example.dao.HibernateDaoSupport;
import org.unnati.example.entities.AmountCurrency;
import org.unnati.example.entities.Item;
import org.unnati.example.entities.MonetaryAmount;

public class App {

    private HibernateDaoSupport dao=new HibernateDaoSupport();
    private long counter=0;

    public static void main(String[] args){
        App app=new App();
        app.runApp();
    }

    public void runApp(){
        System.out.println("Running Application");
         this.createItem();
      //  this.createBid();
    }

    public void createItem(){
        AmountCurrency amountCurrency=new AmountCurrency();
        amountCurrency.setAmount(100);
        amountCurrency.setCurrency("$");

        MonetaryAmount monetaryAmount=new MonetaryAmount();
        monetaryAmount.setCurrency("$");
        monetaryAmount.setAmount(100);


        Item item=new Item();
        item.setAmountCurrency(amountCurrency);
        item.setInitialAmount(monetaryAmount);
        dao.save(item);

        dao.getEntityManager().clear();

        item=dao.getById("Item",item.getId());

        System.out.println("Amount Currency is "+item.getAmountCurrency());
    }

}
