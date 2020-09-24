package com.cp.rrkh.parse.readers;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class JSONFileReader extends AbstractFileReader {

    private final Gson gson = new Gson();

    public JSONFileReader(String fileName) throws IOException {
        super(fileName);
    }

    @Override
    protected String[] mapRow(String line) {
        if (StringUtils.isEmpty(line)) {
            //log
            return new String[0];
        }
        try {
            JsonLine jsonLine = gson.fromJson(line, JsonLine.class);
            return new String[]{jsonLine.getOrderId(), jsonLine.getAmount(), jsonLine.getCurrency(), jsonLine.getComment()};
        } catch (JsonParseException e) {
            //log
            return new String[0];
        }
    }
}
