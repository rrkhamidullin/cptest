package com.cardpay.rrkh.parse;

public interface ParserCreator<V extends FileParser> {
    V create(String fileName) throws Exception;
}
