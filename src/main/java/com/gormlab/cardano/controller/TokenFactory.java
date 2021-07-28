package com.gormlab.cardano.controller;

import com.gormlab.cardano.blockfrost.BlockfrostService;
import com.gormlab.cardano.blockfrost.objects.Asset;
import com.gormlab.cardano.exception.TokenNotAllowedException;
import com.gormlab.cardano.model.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TokenFactory {
    @Autowired
    BlockfrostService blockfrostService;

    @Autowired
    AddressFactory addressFactory;

    @Autowired
    private Environment env;

    @Value("${token.whitelist}")
    List<String> whitelist;

    Set<String> allowedTokens = new HashSet<>();
    private boolean initialised;

    public Token getToken(String assetId) {
        if(isValid(assetId)) {
            Asset asset = blockfrostService.getAsset(assetId);
            if(asset != null) {
                return new Token(asset, blockfrostService.getAssetAddresses(assetId), addressFactory);
            }
        }
        throw new TokenNotAllowedException();
    }

    private boolean isValid(String assetId) {
        initValidTokens();
        return allowedTokens.contains(assetId);
    }

    private void initValidTokens() {
        if(!initialised) {
            initialised = true;
            for(String token : whitelist) {
                allowedTokens.add(env.getProperty("token."+token));
            }
        }
    }
}
