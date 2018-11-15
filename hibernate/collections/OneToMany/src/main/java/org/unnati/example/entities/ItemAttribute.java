package org.unnati.example.entities;

import javax.persistence.*;

@Entity
@Table(name="ItemAttribute")
public class ItemAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "ItemId")
    private Item item;

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

    /**
     * ManyToOne   -> There is association between item and item attribute table
     * JoinColumn  ->  This will define the property of join column
     *
     * */
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
