package com.gormlab.cardano.blockfrost.objects;

import java.io.Serializable;
import java.util.List;

public class AssetAddressList implements Serializable {
    public List<AssetAddress> getAssetAddresses() {
        return assetAddresses;
    }

    public void setAssetAddresses(List<AssetAddress> assetAddresses) {
        this.assetAddresses = assetAddresses;
    }

    private List<AssetAddress> assetAddresses;
}
