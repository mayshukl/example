package org.unnati.example.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@org.hibernate.annotations.OptimisticLocking(type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate
@Entity
public class Item {
    @Id
   // @GeneratedValue(generator = "Custom-ID-Generator")
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;



    private String name;

    @org.hibernate.annotations.OptimisticLock(excluded = true)
    private double price;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
