package com.cp.rrkh.parse;

public class FormatNotSupportedException extends RuntimeException {

    public FormatNotSupportedException(String fileName) {
        super("File format is not supported: " + fileName);
    }
}
