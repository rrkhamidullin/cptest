package com.cp.rrkh.readers;

import lombok.Data;

/**
 * POJO для чтения объектов из JSON файлов.
 */
@Data
public class JsonLine {
    String orderId;
    String amount;
    String currency;
    String comment;
}
