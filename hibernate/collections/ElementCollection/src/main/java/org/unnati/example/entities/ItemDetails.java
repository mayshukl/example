package org.unnati.example.entities;

import javax.persistence.*;
import java.util.*;

@Embeddable
public class ItemDetails {

    @ElementCollection
    @JoinTable(name="ItemWarehouse",joinColumns = @JoinColumn(name="ItemId"))
    private Set<Address> warehouseAddress=new HashSet<>();

    @ElementCollection
    @JoinTable(name="ItemStockDetail",joinColumns = @JoinColumn(name="ItemId"))
    @MapKeyColumn(name="date")
    private  Map<Date,ItemStockDetail> itemStockDetail=new HashMap<>();

    public Set<Address> getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(Set<Address> warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public Map<Date, ItemStockDetail> getItemStockDetail() {
        return itemStockDetail;
    }

    public void setItemStockDetail(Map<Date, ItemStockDetail> itemStockDetail) {
        this.itemStockDetail = itemStockDetail;
    }
}
