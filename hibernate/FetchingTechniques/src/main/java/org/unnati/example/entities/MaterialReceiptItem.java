package org.unnati.example.entities;

import javax.persistence.*;

@Entity
public class MaterialReceiptItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
   // @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name="ItemId")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MaterialReceiptId")
    private MaterialReceipt materialReceipt;


    @ManyToOne(fetch = FetchType.LAZY)
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "UserId")
    private UserProfile addedBy;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public MaterialReceipt getMaterialReceipt() {
        return materialReceipt;
    }

    public void setMaterialReceipt(MaterialReceipt materialReceipt) {
        this.materialReceipt = materialReceipt;
    }

    public UserProfile getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserProfile addedBy) {
        this.addedBy = addedBy;
    }

    public MaterialReceiptItem() {
    }

    public MaterialReceiptItem(Item item, MaterialReceipt materialReceipt, UserProfile addedBy) {
        this.item = item;
        this.materialReceipt = materialReceipt;
        this.addedBy = addedBy;
    }
}
