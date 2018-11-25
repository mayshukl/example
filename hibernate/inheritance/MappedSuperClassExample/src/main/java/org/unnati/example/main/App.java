package org.unnati.example.main;

import org.unnati.example.dao.HibernateDaoSupport;
import org.unnati.example.entities.BankingDetails;
import org.unnati.example.entities.CreditCard;
import org.unnati.example.entities.User;

import java.util.Calendar;
import java.util.Date;
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
   //     this.createUser();
          this.createBankingDetails();
    }

    public void createUser(){
        User user1=new User();
        user1.setName("User1");
        User user2=new User();
        user2.setName("User1");
        dao.getEntityManager().getTransaction().begin();
        dao.getEntityManager().persist(user1);
        dao.getEntityManager().persist(user2);
        dao.getEntityManager().getTransaction().commit();

        System.out.println("User1 id is "+user1.getId());
        System.out.println("User2 id is "+user2.getId());

    }

    public void createBankingDetails(){
        Calendar calendar=Calendar.getInstance();
        calendar.setWeekDate(2025,15,2);
        CreditCard cd=new CreditCard();
        cd.setCardNumber(1234567890L);
        cd.setExpDate(new Date(calendar.getTimeInMillis()));
        cd.setCvvNumber(123);
        cd.setOwner("Owner1");
        dao.save(cd);

       // dao.getAll("CreditCard");
        List<BankingDetails> bankingDetails=dao.getAll("org.unnati.example.entities.BankingDetails");
        System.out.println("Size is "+bankingDetails.size());
    }

}
