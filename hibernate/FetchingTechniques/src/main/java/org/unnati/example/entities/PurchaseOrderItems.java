package org.unnati.example.entities;

import javax.persistence.*;

@Entity
public class PurchaseOrderItems {

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }



    @EmbeddedId
    private PurchaseOrderItemId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "poId",insertable = false,updatable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="itemId",insertable = false,updatable = false)
    private Item  item;

    public PurchaseOrderItems(PurchaseOrder purchaseOrder, Item item) {
        this.purchaseOrder = purchaseOrder;
        this.item = item;
        this.id=new PurchaseOrderItemId(purchaseOrder.getId(),item.getId());
    }

    public PurchaseOrderItems() {
    }
}
