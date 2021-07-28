package com.gormlab.cardano.model;

import com.gormlab.cardano.blockfrost.objects.StakePoolMetadata;

import java.io.Serializable;

public class Pool implements Serializable {
    public String getPoolId() {
        return poolId;
    }

    public void setPoolId(String poolId) {
        this.poolId = poolId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    private String poolId;
    private String ticker;

    public Pool(String stakePoolid, StakePoolMetadata stakePoolMetadata) {
        this.poolId = stakePoolid;
        this.ticker = stakePoolMetadata != null ? stakePoolMetadata.getTicker() : "";
    }
}
