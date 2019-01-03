package org.unnati.example.entities;

import org.unnati.example.entities.listeners.GenericEntityListener;
import org.unnati.example.entities.listeners.ItemListener;

import javax.persistence.*;

@Entity
@EntityListeners({ItemListener.class,GenericEntityListener.class})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

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

    @PreRemove
    public void beforeDeleteItem(){
        System.out.println("Item : Before Deleting Item");
    }

    @PostRemove
    public void afterDeleteItem(){
        System.out.println("Item : After Deleting Item");
    }
}
