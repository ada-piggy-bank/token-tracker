package com.gormlab.cardano.model;

import com.gormlab.cardano.blockfrost.objects.Asset;
import com.gormlab.cardano.blockfrost.objects.AssetAddress;
import com.gormlab.cardano.controller.AddressFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class Token implements Serializable {
    private final String assetId;
    private final Map<String, Address> addressMap = new HashMap<>();
    private final String name;

    public Token(Asset asset, AssetAddress[] assetAddressList, AddressFactory addressFactory) {
        this.assetId = asset.getAsset();
        this.name = asset.getAsset_name();
        for (AssetAddress assetAddress : assetAddressList) {
            Address address = addressFactory.getAddress(assetAddress.getAddress());
            address.setQuantity(assetAddress.getQuantity());
            addressMap.put(assetAddress.getAddress(), address);
        }
    }

    public Token() {
        this.assetId = "";
        this.name = "";
    }


    public String getAssetId() {
        return assetId;
    }

    public Map<String, Address> getAddressMap() {
        return addressMap;
    }

    public String getName() {
        return name;
    }

}
