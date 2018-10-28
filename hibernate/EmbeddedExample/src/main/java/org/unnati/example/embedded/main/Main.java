package org.unnati.example.embedded.main;

import org.unnati.example.embedded.entities.Item;
import org.unnati.example.embedded.org.unnati.example.embedded.TxnManager;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Embedded Example");
        runApp();
    }


    public static void runApp(){

        Item item= new Item();

        item.setName("Item1");

        TxnManager txnManager=new TxnManager();

        txnManager.save(item);


    }


}
