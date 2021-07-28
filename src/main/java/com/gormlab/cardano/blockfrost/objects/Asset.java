package com.gormlab.cardano.blockfrost.objects;

import java.io.Serializable;

public class Asset implements Serializable {
    private String asset;
    private String policy_id;
    private String asset_name;
    private String fingerprint;
    private String quantity;
    private String initial_mint_tx_hash;
    private int mint_or_burn_count;
    private Object onchain_metadata;
    private Object metadata;

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getPolicy_id() {
        return policy_id;
    }

    public void setPolicy_id(String policy_id) {
        this.policy_id = policy_id;
    }

    public String getAsset_name() {
        return asset_name;
    }

    public void setAsset_name(String asset_name) {
        this.asset_name = asset_name;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getInitial_mint_tx_hash() {
        return initial_mint_tx_hash;
    }

    public void setInitial_mint_tx_hash(String initial_mint_tx_hash) {
        this.initial_mint_tx_hash = initial_mint_tx_hash;
    }

    public int getMint_or_burn_count() {
        return mint_or_burn_count;
    }

    public void setMint_or_burn_count(int mint_or_burn_count) {
        this.mint_or_burn_count = mint_or_burn_count;
    }

    public Object getOnchain_metadata() {
        return onchain_metadata;
    }

    public void setOnchain_metadata(Object onchain_metadata) {
        this.onchain_metadata = onchain_metadata;
    }

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }
}
