package org.unnati.example.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ItemDetails {

    @Id
    @GeneratedValue(generator = "ItemIdGenerationLogic")
    @GenericGenerator(
            name="ItemIdGenerationLogic",
            strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(
                    name="property" ,value = "item"
            )

    )
    private int id;

    private double salesPrice;
    private double purchasePrice;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Item item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
