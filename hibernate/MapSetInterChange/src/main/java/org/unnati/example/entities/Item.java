package org.unnati.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Item {

    @Id
    private String id;
    private String name;

    @ElementCollection
    @JoinTable(name = "ItemAttribute",joinColumns = @JoinColumn(name = "ItemId"))
    @MapKeyColumn(name = "AttrId")
    @Column(name="Value")
    Map<String,ItemAttribute> itemAttributeMap=new HashMap<String, ItemAttribute>();

}
