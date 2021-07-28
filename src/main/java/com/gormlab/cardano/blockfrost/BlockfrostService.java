package com.gormlab.cardano.blockfrost;

import com.gormlab.cardano.blockfrost.objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class BlockfrostService {

    @Value("${blockfrost.api.key}")
    String apiKey;

    public static final int SHORT_CLEAR_PERIOD = 900000;
    public static final int DAILY_PERIOD = 86400000;
    RestTemplateBuilder builder = new RestTemplateBuilder();
    Map<String, Asset> assetMap = new HashMap<>();
    Map<String, AssetAddress[]> assetAddressMap = new HashMap<>();
    Map<String, Address> addressMap = new HashMap<>();
    Map<String, StakePoolMetadata> stakePoolMetadataMap = new HashMap<>();
    Map<String, Account> accountMap = new HashMap<>();

    private RestTemplate restTemplate = builder.build();
    private HttpEntity entity;



    public BlockfrostService() {
        Timer dailyTimer = new Timer();
        Timer shortLifeTimer = new Timer();

        dailyTimer.schedule(clearDailyMaps, 0, DAILY_PERIOD);
        shortLifeTimer.schedule(clearShortLifeMaps, 0, SHORT_CLEAR_PERIOD);
    }


    public Asset getAsset(String assetId) {
        return assetMap.computeIfAbsent(assetId, k -> requestAsset(k));
    }

    public AssetAddress[] getAssetAddresses(String assetId) {
        return assetAddressMap.computeIfAbsent(assetId, k -> requestAssetAddressList(k));
    }

    public Address getAddress(String addressId) {
        return addressMap.computeIfAbsent(addressId, k -> requestAddress(k));
    }

    public StakePoolMetadata getStakePoolMetadata(String stakePoolId) {
        return stakePoolMetadataMap.computeIfAbsent(stakePoolId, k -> requestStakePoolMetadata(k));
    }

    public Account getAccount(String stakeAddress) {
        return accountMap.computeIfAbsent(stakeAddress, k -> requestAccount(k));
    }

    private Account requestAccount(String stakeAddress) {
        getHttpEntity();
        try {
            ResponseEntity<Account> account = restTemplate.exchange(
                    "https://cardano-mainnet.blockfrost.io/api/v0/accounts/" + stakeAddress, HttpMethod.GET, entity, Account.class);
            return account.getBody();
        } catch (RuntimeException e) {
            return null;
        }
    }

    private StakePoolMetadata requestStakePoolMetadata(String stakePoolId) {
        getHttpEntity();
        try {
            ResponseEntity<StakePoolMetadata> stakePoolMetadata = restTemplate.exchange(
                    "https://cardano-mainnet.blockfrost.io/api/v0/pools/" + stakePoolId + "/metadata", HttpMethod.GET, entity, StakePoolMetadata.class);
            return stakePoolMetadata.getBody();
        } catch (RuntimeException e) {
            return null;
        }
    }


    private Address requestAddress(String addressId) {
        getHttpEntity();
        ResponseEntity<Address> address = restTemplate.exchange(
                "https://cardano-mainnet.blockfrost.io/api/v0/addresses/" + addressId, HttpMethod.GET, entity, Address.class);
        return address.getBody();
    }

    private void getHttpEntity() {
        if(entity == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("project_id", apiKey);
            entity = new HttpEntity(headers);
        }
    }

    private AssetAddress[] requestAssetAddressList(String assetId) {
        getHttpEntity();
        ResponseEntity<AssetAddress[]> assetAddressList = restTemplate.exchange(
                "https://cardano-mainnet.blockfrost.io/api/v0/assets/" + assetId + "/addresses", HttpMethod.GET, entity, AssetAddress[].class);
        return assetAddressList.getBody();
    }

    private Asset requestAsset(String assetId) {
        getHttpEntity();
        ResponseEntity<Asset> asset = restTemplate.exchange(
                "https://cardano-mainnet.blockfrost.io/api/v0/assets/" + assetId, HttpMethod.GET, entity, Asset.class);
        return asset.getBody();
    }

    TimerTask clearDailyMaps = new TimerTask() {
        @Override
        public void run() {
            stakePoolMetadataMap.clear();
        }
    };

    TimerTask clearShortLifeMaps = new TimerTask() {
        @Override
        public void run() {
            assetAddressMap.clear();
            accountMap.clear();
        }
    };

}
