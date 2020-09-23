package com.cardpay.rrkh.parse;

import lombok.Data;

@Data
public class JsonLine {
    String orderId;
    String amount;
    String currency;
    String comment;
}
