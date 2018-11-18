package org.unnati.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "ItemCategory",
               joinColumns = @JoinColumn(name="ItemId"),
               inverseJoinColumns = @JoinColumn(name="CategoryId"))
    private
    Collection<Category> categories=new ArrayList<>();


    @OneToMany(mappedBy = "item")
    private
    Collection<PurchaseOrderItem> purchaseOrders=new ArrayList<>();


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

    /***
     * ManyToMany   --> This will create a default table.
     * JoinTable    --> This will change default properties of the table created by ManyToMany
     *
     * */
    public Collection<Category> getCategories() {
        return categories;
    }

    public void setCategories(Collection<Category> categories) {
        this.categories = categories;
    }

    public Collection<PurchaseOrderItem> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(Collection<PurchaseOrderItem> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }
}
