package org.unnati.example.entities;

import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@org.hibernate.envers.Audited
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public String getPoName() {
        return poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    private String poName;

    //@org.hibernate.envers.Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name="PurchaseOrderItem",joinColumns = @JoinColumn(name="PurchaseOrderId"),inverseJoinColumns = @JoinColumn(name = "ItemId"))
    private Collection<Item> items=new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }
}
