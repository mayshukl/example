package org.unnati.example.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Embedded
    private ItemPrimaryDetail itemPrimaryDetail;

    @Embedded
    private ItemDetails itemDetails;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ItemPrimaryDetail getItemPrimaryDetail() {
        return itemPrimaryDetail;
    }

    public void setItemPrimaryDetail(ItemPrimaryDetail itemPrimaryDetail) {
        this.itemPrimaryDetail = itemPrimaryDetail;
    }

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }
}
