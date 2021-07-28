package com.gormlab.cardano.controller;

import com.gormlab.cardano.blockfrost.BlockfrostService;
import com.gormlab.cardano.model.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoolFactory {
    @Autowired
    BlockfrostService blockfrostService;

    public Pool getPool(String poolId) {
        return new Pool(poolId, blockfrostService.getStakePoolMetadata(poolId));
    }
}
