package com.cp.rrkh.readers;

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
