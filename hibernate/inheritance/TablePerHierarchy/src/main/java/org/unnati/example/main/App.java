package org.unnati.example.main;

import org.unnati.example.dao.HibernateDaoSupport;
import org.unnati.example.entities.BankAccount;
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
        this.createUser();
   //       this.createBankingDetails();
    }

    public void createUser(){
        User user1=new User();
        user1.setName("User1");

        this.createBankingDetails(user1);

        dao.save(user1);

        dao.getEntityManager().clear();

        System.out.println("Bank Account Details "+((User)dao.getById("User", user1.getId())).getBankingDetails().size());
    }

    public void createBankingDetails(User user){
        Calendar calendar=Calendar.getInstance();
        calendar.setWeekDate(2025,15,2);
        CreditCard cd=new CreditCard();
        cd.setCardNumber(1234567890L);
        cd.setExpDate(new Date(calendar.getTimeInMillis()));
        cd.setCvvNumber(123);
        cd.setOwner("Owner1");

        cd.setUser(user);
        user.getBankingDetails().add(cd);


        BankAccount bc=new BankAccount();
        bc.setAccountNumber(123445);
        bc.setBankName("ABC");
        bc.setIfscCode("IFSC001");

        bc.setUser(user);
        user.getBankingDetails().add(bc);



    }

}
