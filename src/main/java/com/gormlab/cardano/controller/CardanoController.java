package com.gormlab.cardano.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CardanoController {

    @Autowired
    TokenFactory tokenFactory;

    @GetMapping("/token")
    @ResponseBody
    public ResponseEntity<Object> getToken(@RequestParam(value = "assetId") String assetId) {
        try {
            return ResponseEntity.ok(tokenFactory.getToken(assetId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{That's a Bad Piggy}");
        }
    }

    @GetMapping("/tokens")
    @ResponseBody
    public ResponseEntity<Object> getToken(@RequestParam(value = "assetId") List<String> assetIds) {
        try {
            return ResponseEntity.ok(tokenFactory.getTokens(assetIds));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{That's a Bad Piggy}");
        }
    }

}
