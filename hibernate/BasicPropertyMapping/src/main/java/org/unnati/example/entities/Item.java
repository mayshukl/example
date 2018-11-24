package org.unnati.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Item {

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    enum ItemType{
        SALES,
        PURCHASE,
        RAW
    }

    @Id
    private int id;

    @Basic(optional = false)
    @Column(updatable = false)
    private String  name;


    @Column(name="PriceInDollar",nullable = false)
    @org.hibernate.annotations.ColumnTransformer(
            read = "PriceInDollar*70",
            write = "?/70"
    )
    private double price;

    /***
     * Value of this field will be calculated at the time of select statement.So If Some one modify the name on insert or update. This field will still show the old value
     **/
    @org.hibernate.annotations.Formula("SUBSTR(name,0,3)")
    private String shortName;

    @Enumerated(EnumType.STRING)
    private ItemType itemType=ItemType.RAW;

    @Lob
    private byte[] image;

    private java.sql.Clob description;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }


}
