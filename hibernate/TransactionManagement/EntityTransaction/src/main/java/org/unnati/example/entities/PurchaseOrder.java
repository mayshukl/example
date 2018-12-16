package org.unnati.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class PurchaseOrder {

    public long getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double totalAmount;

    @OneToMany(mappedBy = "purchaseOrder")
    private Collection<PurchaseOrderItem> items=new ArrayList<>();

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Collection<PurchaseOrderItem> getItems() {
        return items;
    }

    public void setItems(Collection<PurchaseOrderItem> items) {
        this.items = items;
    }
}
