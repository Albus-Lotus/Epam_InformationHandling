package edu.kravchenko.infohandling.reader;

import edu.kravchenko.infohandling.exception.TextException;
import edu.kravchenko.infohandling.validator.TextFileValidator;
import edu.kravchenko.infohandling.validator.impl.TextFileValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextFileReader {
    private static final Logger logger = LogManager.getLogger();

    public TextFileReader() {
    }

    public List<String> readFile(String filePath) throws TextException {
        TextFileValidator textFileValidator = new TextFileValidatorImpl();
        if (!textFileValidator.isValidFile(filePath)) {
            throw new TextException("File path represents invalid file");
        }
        Path path = Paths.get(filePath);
        List<String> textLines;
        try (Stream<String> textLinesStream = Files.lines(path)) {
            textLines = textLinesStream
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new TextException("Error while reading file" + filePath, e);
        }
        logger.log(Level.INFO, "Lines were successfully received");
        return textLines;
    }
}
