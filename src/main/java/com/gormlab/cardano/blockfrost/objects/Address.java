package com.gormlab.cardano.blockfrost.objects;

import java.io.Serializable;

public class Address implements Serializable {
    private String address;
    private Object amount;
    private String stake_address;
    private String type;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getAmount() {
        return amount;
    }

    public void setAmount(Object amount) {
        this.amount = amount;
    }

    public String getStake_address() {
        return stake_address;
    }

    public void setStake_address(String stake_address) {
        this.stake_address = stake_address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
