package org.unnati.example.entities;

import java.io.Serializable;

public class MonetaryAmount implements Serializable {

    private int amount;

    private String currency;

    @Override
    public String toString() {
        return amount+" "+currency;
    }

    public static MonetaryAmount toAmountCurrency(String value){
        String[] args=value.split(" ");
        MonetaryAmount amountCurrency=new MonetaryAmount();
        amountCurrency.setAmount(Integer.parseInt(args[0]));
        amountCurrency.setCurrency(args[1]);
        return amountCurrency;
    }



    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


}
