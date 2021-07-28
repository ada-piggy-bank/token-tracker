package com.gormlab.cardano.blockfrost.objects;

import java.io.Serializable;

public class AssetAddress implements Serializable {
    private String address;
    private String quantity;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
