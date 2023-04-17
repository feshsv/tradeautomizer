package com.tradeautomizer.populatedb;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@NoArgsConstructor
public class CsvToDtos {

    public static Collection<String> readCsv(String filePath) {
        try(var streamOfString = Files.lines(Paths.get(filePath))) {
            return streamOfString.filter(str -> !str.isBlank()).collect(Collectors.toSet());
        } catch (IOException e) {
            return Collections.EMPTY_LIST;
        }
    }
}
