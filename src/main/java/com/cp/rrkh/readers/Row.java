package com.cp.rrkh.readers;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Строка-результат.
 */
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
    private BigDecimal amount;

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
}
