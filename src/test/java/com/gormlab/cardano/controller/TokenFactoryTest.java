package com.gormlab.cardano.controller;

import com.gormlab.cardano.blockfrost.BlockfrostService;
import com.gormlab.cardano.exception.TokenNotAllowedException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestPropertySource(properties = {
        "token.whitelist=MarketPiggy","token.MarketPiggy=21264462b2dedb9a2d3b1db1f7d66ff5f70f63d9a62787c651661e7a4d61726b65745069676779"
})
class TokenFactoryTest {

    @InjectMocks
    TokenFactory tokenFactory;

    @Mock
    BlockfrostService blockfrostService;

    @Mock
    AddressFactory addressFactory;

    @Mock
    Environment env;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(tokenFactory,"whitelist", Collections.singletonList("MarketPiggy"));
        when(env.getProperty("token.MarketPiggy")).thenReturn("21264462b2dedb9a2d3b1db1f7d66ff5f70f63d9a62787c651661e7a4d61726b65745069676779");

    }

    @Test
    public void validateInvalidAssedId() {
        Exception exception = assertThrows(TokenNotAllowedException.class, () -> {
            tokenFactory.getToken("abc");
        });



        verify(blockfrostService, never()).getAsset(any());
    }

    @Test
    public void validateValidAssedId() {
        Exception exception = assertThrows(TokenNotAllowedException.class, () -> {
        tokenFactory.getToken("21264462b2dedb9a2d3b1db1f7d66ff5f70f63d9a62787c651661e7a4d61726b65745069676779");
        });
        verify(blockfrostService).getAsset(any());
    }

}