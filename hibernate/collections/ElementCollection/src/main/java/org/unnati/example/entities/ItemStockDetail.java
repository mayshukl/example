package org.unnati.example.entities;

import javax.persistence.Embeddable;

@Embeddable
public class ItemStockDetail {

    private long stockOpenAt;
    private long stockIn;
    private long stockCloseAt;
    private long stockOut;


    public long getStockOpenAt() {
        return stockOpenAt;
    }

    public void setStockOpenAt(long stockOpenAt) {
        this.stockOpenAt = stockOpenAt;
    }

    public long getStockIn() {
        return stockIn;
    }

    public void setStockIn(long stockIn) {
        this.stockIn = stockIn;
    }

    public long getStockCloseAt() {
        return stockCloseAt;
    }

    public void setStockCloseAt(long stockCloseAt) {
        this.stockCloseAt = stockCloseAt;
    }

    public long getStockOut() {
        return stockOut;
    }

    public void setStockOut(long stockOut) {
        this.stockOut = stockOut;
    }
}
