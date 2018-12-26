package org.unnati.example.entities;

import javax.persistence.NamedEntityGraph;
import javax.persistence.*;
import java.util.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
    @ElementCollection(fetch = FetchType.LAZY)
    @JoinTable(name="ItemAttributes",joinColumns = @JoinColumn(name="ItemId",nullable = false))
    @MapKeyColumn(name="AttributeId")
    private Map<String,ItemAttribute> itemAttributes=new HashMap<>();

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


    public Map<String, ItemAttribute> getItemAttributes() {
        return itemAttributes;
    }

    public void setItemAttributes(Map<String, ItemAttribute> itemAttributes) {
        this.itemAttributes = itemAttributes;
    }
}
