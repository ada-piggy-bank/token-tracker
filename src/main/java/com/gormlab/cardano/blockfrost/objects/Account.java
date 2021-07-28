package com.gormlab.cardano.blockfrost.objects;

import java.io.Serializable;

public class Account implements Serializable {
    private String stake_address;
    private boolean active;
    private int active_epoch;
    private String controlled_amount;
    private String rewards_sum;
    private String withdrawals_sum;
    private String reserves_sum;
    private String treasury_sum;
    private String withdrawable_amount;
    private String pool_id;

    public String getStake_address() {
        return stake_address;
    }

    public void setStake_address(String stake_address) {
        this.stake_address = stake_address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getActive_epoch() {
        return active_epoch;
    }

    public void setActive_epoch(int active_epoch) {
        this.active_epoch = active_epoch;
    }

    public String getControlled_amount() {
        return controlled_amount;
    }

    public void setControlled_amount(String controlled_amount) {
        this.controlled_amount = controlled_amount;
    }

    public String getRewards_sum() {
        return rewards_sum;
    }

    public void setRewards_sum(String rewards_sum) {
        this.rewards_sum = rewards_sum;
    }

    public String getWithdrawals_sum() {
        return withdrawals_sum;
    }

    public void setWithdrawals_sum(String withdrawals_sum) {
        this.withdrawals_sum = withdrawals_sum;
    }

    public String getReserves_sum() {
        return reserves_sum;
    }

    public void setReserves_sum(String reserves_sum) {
        this.reserves_sum = reserves_sum;
    }

    public String getTreasury_sum() {
        return treasury_sum;
    }

    public void setTreasury_sum(String treasury_sum) {
        this.treasury_sum = treasury_sum;
    }

    public String getWithdrawable_amount() {
        return withdrawable_amount;
    }

    public void setWithdrawable_amount(String withdrawable_amount) {
        this.withdrawable_amount = withdrawable_amount;
    }

    public String getPool_id() {
        return pool_id;
    }

    public void setPool_id(String pool_id) {
        this.pool_id = pool_id;
    }
}
