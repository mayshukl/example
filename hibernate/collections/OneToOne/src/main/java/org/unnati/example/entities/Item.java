package org.unnati.example.entities;

import javax.persistence.*;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    /**
     * OneToOne   -- > This will create a column.this one to one allows many item with same item type because "unique" attribute is false
     * Joincolumn  --> Define column properties.
     *
     **/
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ItemTypeId",nullable = false)
    private ItemType itemType;

    /***
     *
     * mappedBy  --> This will delegate the responsibility to ItemDetails.So No column will be added
     *
     */
    @OneToOne(mappedBy = "item",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private ItemDetails itemDetail;


    /**
     *
     * JointTable will create new Table with given name
     *
     * */
    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinTable(name="ItemWarehouse",joinColumns =@JoinColumn(name="ItemId"),inverseJoinColumns = @JoinColumn(name="WarehouseId"))
    private Warehouse warehouse;


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }


    public ItemDetails getItemDetail() {
        return itemDetail;
    }

    public void setItemDetail(ItemDetails itemDetail) {
        this.itemDetail = itemDetail;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}
