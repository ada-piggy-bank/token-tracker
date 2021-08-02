package com.gormlab.cardano.model;

import com.gormlab.cardano.blockfrost.objects.Asset;
import com.gormlab.cardano.blockfrost.objects.AssetAddress;
import com.gormlab.cardano.controller.AddressFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Token implements Serializable {
    private final String assetId;
    private final Map<String, Address> addressMap = new HashMap<>();
    private final String name;
    private final String image;
    private final String type;
    private final String owner;
    private final int quantity;
    private final String policyId;
    private final String collection;
    private final String website;
    private final List<String> details;
    private final String rarity;

    public Token(Asset asset, AssetAddress[] assetAddressList, AddressFactory addressFactory) {
        this.assetId = asset.getAsset();

        for (AssetAddress assetAddress : assetAddressList) {
            Address address = addressFactory.getAddress(assetAddress.getAddress());
            address.setQuantity(assetAddress.getQuantity());
            addressMap.put(assetAddress.getAddress(), address);
        }
        quantity = Integer.valueOf(asset.getQuantity());
        policyId = asset.getPolicy_id();
        Map metadata = (Map) asset.getOnchain_metadata();
        this.name = (String) metadata.get("name");
        image = (String) metadata.get("image");
        type = (String) metadata.get("Type");
        owner = (String) metadata.get("Owner");


        collection = (String) metadata.get("Collection");
        website = (String) metadata.get("website");
        details = (List<String>) metadata.get("Details");
        rarity = (String) metadata.get("Rarity");
    }

    public Token() {
        this.assetId = "";
        this.name = "";
        image = "";
        type = "";
        owner = "";
        quantity = 0;
        policyId = "";
        collection = "";
        website = "";
        details = new ArrayList<>();
        rarity = "";
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

    public String getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public String getOwner() {
        return owner;
    }

    public String getRarity() {
        return rarity;
    }

    public List<String> getDetails() {
        return details;
    }

    public String getWebsite() {
        return website;
    }

    public String getCollection() {
        return collection;
    }

    public String getPolicyId() {
        return policyId;
    }

    public int getQuantity() {
        return quantity;
    }
}
