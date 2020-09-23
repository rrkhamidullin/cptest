package com.cardpay.rrkh.parse;

import com.google.gson.Gson;
import org.springframework.util.StringUtils;

public class JSONFileParser extends AbstractFileParser {

    private Gson gson = new Gson();

    public JSONFileParser(String fileName) throws Exception {
        super(fileName);
    }

    @Override
    protected String[] mapRow(String line) {
        if (StringUtils.isEmpty(line)) {
            return new String[0];
        }
        JsonLine jsonLine = gson.fromJson(line, JsonLine.class);
        return new String[]{jsonLine.getOrderId(), jsonLine.getAmount(), jsonLine.getCurrency(), jsonLine.getComment()};
    }
}
