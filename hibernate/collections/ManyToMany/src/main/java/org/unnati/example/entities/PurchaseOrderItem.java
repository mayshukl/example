package org.unnati.example.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@org.hibernate.annotations.Immutable
public class PurchaseOrderItem {

    @Embeddable
    public static class Id implements Serializable{

        private int itemId;

        @Column(name="PoId")
        private int purchaseOrderId;

        public Id(PurchaseOrder purchaseOrder,Item item){
            this.purchaseOrderId=purchaseOrder.getId();
            this.itemId=item.getId();
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getPurchaseOrderId() {
            return purchaseOrderId;
        }

        public void setPurchaseOrderId(int purchaseOrderId) {
            this.purchaseOrderId = purchaseOrderId;
        }
    }

    @EmbeddedId
    private Id id;

    @Column(updatable = false)
    private Date dateAdded;
    @Column(updatable = false)
    private String userAdded;

    @ManyToOne
    @JoinColumn(name ="PoId",insertable = false,updatable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name ="ItemId",insertable = false,updatable = false)
    private Item item;



    public PurchaseOrderItem(String userName,PurchaseOrder purchaseOrder,Item item){
        this.id=new Id(purchaseOrder,item);
        this.purchaseOrder=purchaseOrder;
        this.item=item;

        this.userAdded=userName;
        this.dateAdded=new Date();
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getUserAdded() {
        return userAdded;
    }

    public void setUserAdded(String userAdded) {
        this.userAdded = userAdded;
    }
}
