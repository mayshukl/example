package org.unnati.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String OrderNo;


    @OneToMany(mappedBy = "purchaseOrder")
    private
    Collection<PurchaseOrderItem> items=new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public Collection<PurchaseOrderItem> getItems() {
        return items;
    }

    public void setItems(Collection<PurchaseOrderItem> items) {
        this.items = items;
    }
}
