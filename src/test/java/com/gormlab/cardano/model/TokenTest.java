package com.gormlab.cardano.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gormlab.cardano.blockfrost.objects.Asset;
import com.gormlab.cardano.blockfrost.objects.AssetAddress;
import com.gormlab.cardano.controller.AddressFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
class TokenTest {

    private final AssetAddress[] addresslist = new AssetAddress[0];
    private Asset asset;
    @Mock
    private AddressFactory addressFactory;


    @BeforeEach
    public void setup() throws JsonProcessingException {
        asset = createAssetFromJson();
    }

    @Test
    public void tokenContainsMetadataFromAsset() {
        Token token = new Token(asset, addresslist, addressFactory);

        assertThat(token.getAssetId(), is("21264462b2dedb9a2d3b1db1f7d66ff5f70f63d9a62787c651661e7a4d61726b65745069676779"));
        assertThat(token.getPolicyId(), is("21264462b2dedb9a2d3b1db1f7d66ff5f70f63d9a62787c651661e7a"));
        assertThat(token.getQuantity(), is(1));
        assertThat(token.getName(), is("This little piggy went to market"));
        assertThat(token.getImage(), is("ipfs://ipfs/QmTcLzGzAcuxDgfvAvm3DUmTPEXDvGCq6yuyXfdidPW5hz"));
        assertThat(token.getType(), is("Game"));
        assertThat(token.getOwner(), is("Bearer"));
        assertThat(token.getRarity(), is("Unique"));
        assertThat(token.getDetails().size(), is(7));
        assertThat(token.getWebsite(), is("http://piggy.trendingpools.com/piggies/market"));
        assertThat(token.getCollection(), is("Five little piggies"));
    }

    private Asset createAssetFromJson() throws JsonProcessingException {
        String json = "{" +
                "                \"asset\": \"21264462b2dedb9a2d3b1db1f7d66ff5f70f63d9a62787c651661e7a4d61726b65745069676779\"," +
                "                \"policy_id\": \"21264462b2dedb9a2d3b1db1f7d66ff5f70f63d9a62787c651661e7a\"," +
                "                \"asset_name\": \"4d61726b65745069676779\"," +
                "                \"fingerprint\": \"asset1rddr446y2q8cmzpla55d7xapsw2g9gypqyuak3\"," +
                "                \"quantity\": \"1\"," +
                "                \"initial_mint_tx_hash\": \"384e81d6cd1ac82845db0078b9abe350cd3c86f75044c5fd75aee256ce4bbf54\"," +
                "                \"mint_or_burn_count\": 1," +
                "                \"onchain_metadata\": {" +
                "                \"name\": \"This little piggy went to market\"," +
                "                    \"image\": \"ipfs://ipfs/QmTcLzGzAcuxDgfvAvm3DUmTPEXDvGCq6yuyXfdidPW5hz\"," +
                "                    \"Type\": \"Game\"," +
                "                    \"Owner\": \"Bearer\"," +
                "                    \"Policy\": \"21264462b2dedb9a2d3b1db1f7d66ff5f70f63d9a62787c651661e7a\"," +
                "                    \"Rarity\": \"Unique\"," +
                "                    \"Details\": [" +
                "                    \"Please trade this token.\"," +
                "                    \"The bearer can return it to its designated pot address\"," +
                "                    \"at the end of the round to claim its prize.\"," +
                "                    \"Alternatively, it may be returned with all 5 piggies\"," +
                "                    \"to the jackpot address to claim the jackpot.\"," +
                "                    \"Be sure to claim before it expires.\"," +
                "                    \"See the website for full details.\"" +
                "                    ]," +
                "                    \"website\": \"http://piggy.trendingpools.com/piggies/market\"," +
                "                    \"Collection\": \"Five little piggies\"" +
                "        }," +
                "         \"metadata\": null " +
                "}";

        ObjectMapper mapper = new ObjectMapper();

        Asset asset = mapper.readValue(json, Asset.class);
        return asset;
    }

}