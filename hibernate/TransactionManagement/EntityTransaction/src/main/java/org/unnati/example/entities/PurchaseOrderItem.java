package org.unnati.example.entities;

import javax.persistence.*;

@Entity
public class PurchaseOrderItem {
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    @EmbeddedId
    private ID id;

    @Version
    private long version;

    private double price;

    @ManyToOne
    @JoinColumn(name = "poId",insertable=false, updatable=false)
    private PurchaseOrder purchaseOrder;

    public PurchaseOrderItem(long poId,long itemId){
        this.id=new ID(poId,itemId);
    }

    public PurchaseOrderItem() {
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
