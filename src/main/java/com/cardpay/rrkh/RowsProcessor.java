package com.cardpay.rrkh;

import com.cardpay.rrkh.parse.FileParser;
import com.cardpay.rrkh.parse.Row;
import org.springframework.scheduling.annotation.Async;

public class RowsProcessor {

    @Async
    public void process(FileParser fileParser) {
        try {
            fileParser.rows().map(Row::toJsonObject).forEach(System.out::println);
        } finally {
            try {
                fileParser.close();
            } catch (Exception e) {
                //log
            }
        }
    }
}
