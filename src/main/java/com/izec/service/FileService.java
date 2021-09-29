package com.izec.service;


import com.izec.exception.FileParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Service
public class FileService {

    /**
     * Метод парсит файл
     * @return
     */
    public List<String> fileParsing() throws FileParseException {
        List<String> list = null;
        try {
            Path path = Paths.get("/Users/z19572617/IdeaProjects/City2/src/main/resources/city.txt");
            list = Files.readAllLines(path);
        } catch (IOException exception) {
            throw new FileParseException();
        }
        return list;
    }


}
