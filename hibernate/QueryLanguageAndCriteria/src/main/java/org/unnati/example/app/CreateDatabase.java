package org.unnati.example.app;

import org.unnati.example.entities.Bid;
import org.unnati.example.entities.Item;
import org.unnati.example.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;

public class CreateDatabase {
    private static int userCounter=0;
    private static int itemCounter=0;

    public static int latestCreatedUserId=-1;

    public static void createDatabase(EntityManager entityManager){
        User user=createAndGetUser(entityManager);
        Item item=createItem(entityManager,user);
        createBid(entityManager,item,new BigDecimal(100));
        createBid(entityManager,item,new BigDecimal(90));
        createBid(entityManager,item,new BigDecimal(101));
        createBid(entityManager,item,new BigDecimal(111));
    }

    public static int createUser(EntityManager entityManager){
        User user=new User();
        user.setName("User"+userCounter++);

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return latestCreatedUserId = user.getId();
    }

    public static int createItem(int userId,EntityManager entityManager){
        TypedQuery<User> query=entityManager.createQuery("select u from User u where u.id=:id",User.class);
        query.setParameter("id",userId);
        User user=query.getSingleResult();

        Item item=new Item();
        item.setName("Item"+itemCounter++);
        item.setSeller(user);

        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
        return item.getId();
    }

    public static User createAndGetUser(EntityManager entityManager){
        User user=new User();
        user.setName("User"+userCounter++);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }
    public static Item createItem(EntityManager entityManager,User seller){
        Item item=new Item();
        item.setName("Item"+itemCounter++);
        item.setSeller(seller);

        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();

        return item;
    }
    public static Bid createBid(EntityManager entityManager ,Item item,BigDecimal amount){
        Bid bid=new Bid();
        bid.setAmount(amount);
        bid.setItem(item);

        item.getBids().add(bid);

        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.persist(bid);
        entityManager.getTransaction().commit();
        return bid;
    }

}
