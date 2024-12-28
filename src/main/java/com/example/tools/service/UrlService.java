package com.example.tools.service;

import java.net.URLEncoder;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class UrlService {
    public String encode(String input) throws Exception {
        return URLEncoder.encode(input, StandardCharsets.UTF_8);
    }

    public String decode(String input) throws Exception {
        return URLDecoder.decode(input, StandardCharsets.UTF_8);
    }
} 