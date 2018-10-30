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
}
