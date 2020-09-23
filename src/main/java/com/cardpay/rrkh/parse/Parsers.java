package com.cardpay.rrkh.parse;

import org.springframework.util.StringUtils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public enum Parsers {
    CSV(Collections.singleton("csv"), CSVFileParser::new),
    JSON(Collections.singleton("json"), JSONFileParser::new),
    XLS(Arrays.asList("xls", "xlsx"), s -> {
        throw new NotImplementedException();
    });

    private final Collection<String> extensions;
    private final ParserCreator<? extends FileParser> parserCreator;

    Parsers(Collection<String> extensions, ParserCreator<? extends FileParser> parserCreator) {
        this.extensions = extensions;
        this.parserCreator = parserCreator;
    }

    public static FileParser create(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            // log
            return null;
        }
        String[] split = fileName.split("\\.");
        if (split.length < 2) {
            // log
            return null;
        }
        try {
            return byExtension(split[split.length - 1]).parserCreator.create(fileName);
        } catch (Exception e) {
            // log
            return null;
        }
    }

    private static Parsers byExtension(String extension) {
        return Arrays.stream(Parsers.values())
                .filter(parsers -> parsers.extensions.contains(extension)).findAny()
                .orElseThrow(() -> new FormatNotSupportedException(extension));
    }
}
