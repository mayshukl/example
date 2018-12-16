package org.unnati.example.main;


import org.unnati.example.dao.DaoSupport;
import org.unnati.example.entities.Item;

public class App {

    private DaoSupport dao=new DaoSupport();

    public static void main(String[] args){
        App app=new App();

        app.runApp();
    }

    public void runApp(){

        System.out.println("Running Application");
        try {
            this.createItem();
        }catch(Exception e){

        }
        System.out.println("Number of item in HSQL is "+dao.getAllFromUnit1("Item").size());
    }

    public void createItem(){

        Item item1 =new Item();
        item1.setName("Item1");
        dao.save(item1);




    }

}
