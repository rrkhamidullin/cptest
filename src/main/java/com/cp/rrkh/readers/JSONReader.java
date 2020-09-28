package com.cp.rrkh.readers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;

public class JSONReader extends AbstractReader {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public JSONReader(String fileName, BufferedReader br) {
        super(fileName, br);
    }

    @Override
    protected String[] mapLine(String line) {
        if (StringUtils.isEmpty(line)) {
            return new String[0];
        }
        try {
            JsonLine jsonLine = objectMapper.readValue(line, JsonLine.class);
            return new String[]{jsonLine.getOrderId(), jsonLine.getAmount(), jsonLine.getCurrency(), jsonLine.getComment()};
        } catch (JsonProcessingException e) {
            return new String[0];
        }
    }
}
