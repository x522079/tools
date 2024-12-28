package com.example.tools.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

public class JsonService {
    private final ObjectMapper mapper;
    private final DefaultPrettyPrinter printer;

    public JsonService() {
        mapper = new ObjectMapper();
        
        // 创建自定义的美化打印器
        printer = new DefaultPrettyPrinter();
        DefaultIndenter indenter = new DefaultIndenter("    ", "\n");
        printer.indentArraysWith(indenter);
        printer.indentObjectsWith(indenter);
        
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enable(JsonParser.Feature.ALLOW_COMMENTS);
    }

    public String format(String json) throws Exception {
        Object jsonObj = mapper.readValue(json, Object.class);
        return mapper.writer(printer).writeValueAsString(jsonObj);
    }

    public String compress(String json) throws Exception {
        Object jsonObj = mapper.readValue(json, Object.class);
        mapper.disable(SerializationFeature.INDENT_OUTPUT);
        String result = mapper.writeValueAsString(jsonObj);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return result;
    }
} 