package org.unnati.example.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;


    /**
     * mappedBy         -> The field that owns the relationship. Without this , an extra mapping table will be created with item id and item_attribute_id column.
     * cascade          -> The operations that must be cascaded to the target of the association. In this case it will persist save to ItemAttribute
     * fetch            -> Whether the association should be lazily loaded or must be eagerly fetched. Lazy loaded at time of accessing the getter of object.
     * orphanRemoval    -> Whether to apply the remove operation to entities that have been removed from the relationship and to cascade the remove operation to those entities.
     * */
    @OneToMany(mappedBy = "item" , cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER,orphanRemoval = true)
    private
    List<ItemAttribute> itemAttributes=new ArrayList<>();

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

    public List<ItemAttribute> getItemAttributes() {
        return itemAttributes;
    }

    public void setItemAttributes(List<ItemAttribute> itemAttributes) {
        this.itemAttributes = itemAttributes;
    }
}
