package com.gormlab.cardano.model;

import com.gormlab.cardano.blockfrost.BlockfrostService;
import com.gormlab.cardano.blockfrost.objects.Account;
import com.gormlab.cardano.controller.PoolFactory;

import java.io.Serializable;

public class Address implements Serializable {
    public Pool getStakePool() {
        return stakePool;
    }

    public void setStakePool(Pool stakePool) {
        this.stakePool = stakePool;
    }

    private Pool stakePool;
    private String quantity;

    public String getStakeAddress() {
        return stakeAddress;
    }

    public void setStakeAddress(String stakeAddress) {
        this.stakeAddress = stakeAddress;
    }

    private String stakeAddress;

    public Address(com.gormlab.cardano.blockfrost.objects.Address address) {
        this.stakeAddress = address.getStake_address();

    }

    public void getStakeDetails(BlockfrostService blockfrostService, PoolFactory poolFactory) {
        Account account = blockfrostService.getAccount(this.stakeAddress);
        this.stakePool = account != null ? poolFactory.getPool(account.getPool_id()) : null;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
