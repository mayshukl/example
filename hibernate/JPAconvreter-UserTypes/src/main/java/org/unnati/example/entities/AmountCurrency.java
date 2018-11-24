package org.unnati.example.entities;

import java.io.Serializable;
import java.util.Objects;

public class AmountCurrency implements Serializable {

    private int amount;

    @Override
    public String toString() {
        return amount+" "+currency;
    }

    public static AmountCurrency toAmountCurrency(String value){
        String[] args=value.split(" ");
        AmountCurrency amountCurrency=new AmountCurrency();
        amountCurrency.setAmount(Integer.parseInt(args[0]));
        amountCurrency.setCurrency(args[1]);
        return amountCurrency;
    }

    private String currency;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmountCurrency that = (AmountCurrency) o;
        return amount == that.amount &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
