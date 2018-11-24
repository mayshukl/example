package org.unnati.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//    @org.hibernate.annotations.Type(type = "long")
//    private int quantity;

    private AmountCurrency amountCurrency;

    @org.hibernate.annotations.Type(type = "usd_monetary_amount")
    @org.hibernate.annotations.Columns(columns = {
            @Column(name = "InitialAmount"),
            @Column(name = "InitialCurrency")
    })
    private MonetaryAmount initialAmount;
   // private MonetaryAmount buyNowPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AmountCurrency getAmountCurrency() {
        return amountCurrency;
    }

    public void setAmountCurrency(AmountCurrency amountCurrency) {
        this.amountCurrency = amountCurrency;
    }

    public MonetaryAmount getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(MonetaryAmount initialAmount) {
        this.initialAmount = initialAmount;
    }
}
