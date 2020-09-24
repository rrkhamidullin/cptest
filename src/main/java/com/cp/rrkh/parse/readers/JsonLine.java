package com.cp.rrkh.parse.readers;

import lombok.Data;

/**
 * POJO для чтения JSON-объектов.
 */
@Data
public class JsonLine {
    String orderId;
    String amount;
    String currency;
    String comment;
}
