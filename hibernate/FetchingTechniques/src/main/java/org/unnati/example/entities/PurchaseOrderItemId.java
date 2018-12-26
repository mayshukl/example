package org.unnati.example.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaseOrderItemId  implements Serializable {

    int poId;

    int itemId;


    public PurchaseOrderItemId(){}

    public PurchaseOrderItemId(int poId,int itemId){
        this.itemId=itemId;
        this.poId=poId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrderItemId that = (PurchaseOrderItemId) o;
        return poId == that.poId &&
                itemId == that.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(poId, itemId);
    }
}