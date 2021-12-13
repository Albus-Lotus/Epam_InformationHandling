package edu.kravchenko.infohandling.parser;

import edu.kravchenko.infohandling.entity.InfoComponent;
import edu.kravchenko.infohandling.exception.TextException;

public interface InfoParser {
    InfoComponent parse(String text) throws TextException;
}
