package org.unnati.example.embedded.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * */
@Embeddable
public class ItemDeail {

    private String itemType;

    @Column( nullable = true)
    private double purchaseCost;
    @Column( nullable = true)
    private double salesCost;

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public double getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(double purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public double getSalesCost() {
        return salesCost;
    }

    public void setSalesCost(double salesCost) {
        this.salesCost = salesCost;
    }
}
