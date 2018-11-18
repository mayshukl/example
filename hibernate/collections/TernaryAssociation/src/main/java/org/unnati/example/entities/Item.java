package org.unnati.example.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "ItemCategory" , joinColumns = {@JoinColumn(name="ItemId",nullable = false)})
    private Set<CategorizedItem> categories=new HashSet<>();


    @ManyToMany
    @MapKeyJoinColumn(name="CategoryId")
    @JoinTable(name= "ItemAddedBy",joinColumns = @JoinColumn(name="ItemId"),inverseJoinColumns = @JoinColumn(name="UserId"))
    private Map<Category,User> itemAddedBy = new HashMap<>();

}
