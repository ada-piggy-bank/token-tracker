package com.gormlab.cardano.controller;

import com.gormlab.cardano.blockfrost.BlockfrostService;
import com.gormlab.cardano.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressFactory {
    @Autowired
    BlockfrostService blockfrostService;

    @Autowired
    PoolFactory poolFactory;

    public Address getAddress(String addressId) {
        Address address = new Address(blockfrostService.getAddress(addressId));
        address.getStakeDetails(blockfrostService, poolFactory);
        return address;
    }
}
