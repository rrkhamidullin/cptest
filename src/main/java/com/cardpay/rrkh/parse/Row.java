package com.cardpay.rrkh.parse;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Row {

    /**
     * Идентификатор ордера
     */
    private Long id;

    /**
     * Сумма ордера в центах
     */
    private Long amount;

    /**
     * Валюта суммы ордера
     */
    private String currency;

    /**
     * Комментарий по ордеру
     */
    private String comment;

    /**
     * Имя исходного файла
     */
    private String filename;

    /**
     * Номер строки исходного файла
     */
    private long line;

    /**
     * Результат парсинга записи исходного файла.
     */
    private String result;

    /**
     * ObjectMapper тут будет лишним.
     */
    public String toJsonObject() {
        return "{" +
                "\"id\":" + id +
                ",\"amount\":" + amount +
                ",\"currency\":\"" + currency + "\"" +
                ",\"comment\":\"" + comment + "\"" +
                ",\"filename\":" + filename + "\"" +
                ",\"line\":" + line +
                ",\"result\":" + result + "\"" +
                '}';
    }
}
