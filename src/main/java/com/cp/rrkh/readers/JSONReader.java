package com.cp.rrkh.readers;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;

public class JSONReader extends AbstractReader {

    private final Gson gson = new Gson();

    public JSONReader(String fileName, BufferedReader br) {
        super(fileName, br);
    }

    @Override
    protected String[] mapLine(String line) {
        if (StringUtils.isEmpty(line)) {
            return new String[0];
        }
        try {
            JsonLine jsonLine = gson.fromJson(line, JsonLine.class);
            return new String[]{jsonLine.getOrderId(), jsonLine.getAmount(), jsonLine.getCurrency(), jsonLine.getComment()};
        } catch (JsonParseException e) {
            return new String[0];
        }
    }
}
