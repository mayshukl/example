package org.unnati.example.embedded.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Embedded
    private ItemDeail itemDetail;

    @ElementCollection
    @CollectionTable(name = "itemattribute")
    private Set<ItemAttribute> itemAttributes=new HashSet<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemDeail getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDeail itemDetail) {
        this.itemDetail = itemDetail;
    }
}
