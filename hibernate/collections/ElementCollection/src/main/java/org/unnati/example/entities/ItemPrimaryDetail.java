package org.unnati.example.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Embeddable
public class ItemPrimaryDetail {



    /**
     * This Strategy is used to map alish names of an item in another tables
     * ElementCollection - Define a parent-child relationship
     * CollectionTable   - define the name of intended table name
     * JoinColumn        - define the foreign key column name
     * Column            - define the name of column to hold alish name
     * CollectionId      - Set does not not support it
     *
     * */
    @ElementCollection
    @CollectionTable(name="ItemAlishName" ,joinColumns = @JoinColumn(name="ItemId"))
    @Column(name="AlishName")
    private Set<String> itemAlishNames=new HashSet<>();



    /**
     *
     * CollectionId      - Define the surrogate primary Key in case of Bag type collections
     *
     * GenericGenerator  - Define a identifier generator
     * */

    @ElementCollection
    @CollectionTable(name="ItemAuditLog" ,joinColumns = @JoinColumn(name="ItemId"))
    @Column(name="LogDetail")
    @GenericGenerator(name="customIdGenerator",strategy = "org.unnati.example.entities.generator.CustomIdGenerator")
    @org.hibernate.annotations.CollectionId(
            columns = @Column(name="AuditLogId"),
            type=@org.hibernate.annotations.Type(type = "long"),
            generator = "customIdGenerator"
    )
    private Collection<String> itemAuditLog=new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="ItemImageDetail",joinColumns = @JoinColumn(name="ItemId"))
    @OrderColumn(name="imageIndex")
    @Column(name="ImageName")
    private List<String> itemImageName= new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="ItemNoOfItemSold",joinColumns = @JoinColumn(name="ItemId"))
    @MapKeyColumn(name="Date")
    @Column(name="Quantity")
    private Map<Date,Integer> itemNoOfItemSold= new HashMap<>();



    public Set<String> getItemAlishNames() {
        return itemAlishNames;
    }

    public void setItemAlishNames(Set<String> itemAlishNames) {
        this.itemAlishNames = itemAlishNames;
    }


    public Collection<String> getItemAuditLog() {
        return itemAuditLog;
    }

    public void setItemAuditLog(Collection<String> itemAuditLog) {
        this.itemAuditLog = itemAuditLog;
    }


    /***
     * OrderColumn  - creates a column to hold the index of the list
     *
     **/
    public List<String> getItemImageName() {
        return itemImageName;
    }

    public void setItemImageName(List<String> itemImageName) {
        this.itemImageName = itemImageName;
    }

    /**
     ** MapKeyColumn  - define the key of map
     * **/
    public Map<Date, Integer> getItemNoOfItemSold() {
        return itemNoOfItemSold;
    }

    public void setItemNoOfItemSold(Map<Date, Integer> itemNoOfItemSold) {
        this.itemNoOfItemSold = itemNoOfItemSold;
    }
}
