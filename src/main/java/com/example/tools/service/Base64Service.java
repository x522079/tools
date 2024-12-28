package com.example.tools.service;

import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class Base64Service {
    public String encode(String input) {
        return Base64.getEncoder().encodeToString(
            input.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String decode(String input) {
        byte[] decodedBytes = Base64.getDecoder().decode(input);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
} 