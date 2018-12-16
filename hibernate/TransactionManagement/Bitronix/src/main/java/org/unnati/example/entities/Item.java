package org.unnati.example.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
public class Item {
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;



    private String name;

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
