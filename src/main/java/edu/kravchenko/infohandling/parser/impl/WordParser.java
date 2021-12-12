package edu.kravchenko.infohandling.parser.impl;

import edu.kravchenko.infohandling.entity.ComponentType;
import edu.kravchenko.infohandling.entity.InfoComponent;
import edu.kravchenko.infohandling.entity.TextComposite;
import edu.kravchenko.infohandling.entity.TextSymbol;
import edu.kravchenko.infohandling.exception.TextException;
import edu.kravchenko.infohandling.parser.InfoParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WordParser implements InfoParser {
    private static final Logger logger = LogManager.getLogger();
    private static final WordParser INSTANCE = new WordParser();
    private final String WORD_SPLIT_REGEXP = "";

    private WordParser() {
    }

    public static WordParser getInstance() {
        return INSTANCE;
    }

    @Override
    public InfoComponent parse(String text) throws TextException {
        String[] symbols = text.strip().split(WORD_SPLIT_REGEXP);
        var component = new TextComposite(ComponentType.WORD);
        InfoComponent symbolComponent;
        for (String symbol : symbols) {
            symbolComponent = new TextSymbol(ComponentType.SYMBOL, symbol.charAt(0));
            component.add(symbolComponent);
        }
        logger.log(Level.INFO, "word were successfully parsed: {}", text);
        return component;
    }
}
