package org.unnati.example.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ID implements Serializable {
    private long itemId;

    public ID(long poId,long itemId) {
        this.itemId = itemId;
        this.poId = poId;
    }

    public ID() {
    }

    private long poId;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getPoId() {
        return poId;
    }

    public void setPoId(long poId) {
        this.poId = poId;
    }
}