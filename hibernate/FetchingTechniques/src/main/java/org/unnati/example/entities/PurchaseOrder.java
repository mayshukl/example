package org.unnati.example.entities;

import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@NamedEntityGraph(
        name = "PurchaseOrderGraph",
        attributeNodes = {
             //   @NamedAttributeNode(value="addedBy"),
                @NamedAttributeNode(value="itemsBag",subgraph = "PurchaseOrderGraphItemBag")
        },
        subgraphs = {
                @NamedSubgraph(
                        name="PurchaseOrderGraphItemBag",
                        attributeNodes = {
                                @NamedAttributeNode(value = "item")
                        }
                )
        }
)
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String purchaseOrderNo;


    @OneToMany(mappedBy = "purchaseOrder",fetch = FetchType.LAZY)
    @org.hibernate.annotations.BatchSize(size = 2)
    private Collection<PurchaseOrderItems> itemsBag=new ArrayList<>();



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="addedBy",updatable = false)
    private UserProfile addedBy;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.CreationTimestamp
    private Date addedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="modifiedBy")
    private UserProfile modifiedBy;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @org.hibernate.annotations.UpdateTimestamp
    private Date modifiedOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    }

    public Collection<PurchaseOrderItems> getItemsBag() {
        return itemsBag;
    }

    public void setItemsBag(Collection<PurchaseOrderItems> itemsBag) {
        this.itemsBag = itemsBag;
    }

    public UserProfile getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(UserProfile addedBy) {
        this.addedBy = addedBy;
    }

    public Date getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(Date addedOn) {
        this.addedOn = addedOn;
    }

    public UserProfile getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UserProfile modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
