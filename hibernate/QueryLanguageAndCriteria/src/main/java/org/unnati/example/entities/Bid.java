package org.unnati.example.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private BigDecimal amount;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ItemId")
    private Item item;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bid bid = (Bid) o;
        return id == bid.id &&
                Objects.equals(amount, bid.amount) &&
                Objects.equals(item, bid.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
}
