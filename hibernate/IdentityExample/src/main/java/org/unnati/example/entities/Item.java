package org.unnati.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Item {


    /***
     *
     * Id --> This identify the identity column in Entity
     *
     * GenerationType.AUTO      --> This is default strategy.JPA will pick most appropriate strategy according to database dialect
     * GenerationType.IDENTITY  --> This creates a auto increment column .
     * GenerationType.SEQUENCE  --> This creates a sequence start with 1 increment by 1. Same Sequence will be used to get id for Entity
     * GenerationType.TABLE    --> this will create a table and will keep next id value for Entity in last inserted row in the created table .
     *
     *
     * */
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;

    private String name;


}
