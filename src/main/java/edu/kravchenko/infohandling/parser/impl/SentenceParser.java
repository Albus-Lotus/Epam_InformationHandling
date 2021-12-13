package edu.kravchenko.infohandling.parser.impl;

import edu.kravchenko.infohandling.entity.ComponentType;
import edu.kravchenko.infohandling.entity.InfoComponent;
import edu.kravchenko.infohandling.entity.TextComposite;
import edu.kravchenko.infohandling.exception.TextException;
import edu.kravchenko.infohandling.parser.InfoParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SentenceParser implements InfoParser {
    private static final Logger logger = LogManager.getLogger();
    private static final SentenceParser INSTANCE = new SentenceParser();
    private final String SENTENCE_SPLIT_REGEXP = "\\s+";
    private InfoParser nextParser = LexemeParser.getInstance();

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {
        return INSTANCE;
    }

    @Override
    public InfoComponent parse(String text) throws TextException {
        String[] lexemes = text.strip().split(SENTENCE_SPLIT_REGEXP);
        var component = new TextComposite(ComponentType.SENTENCE);
        InfoComponent lexemeComponent;
        for (String lexeme : lexemes) {
            lexemeComponent = nextParser.parse(lexeme);
            component.add(lexemeComponent);
        }
        logger.log(Level.INFO, "sentence were successfully parsed: {}", text);
        return component;
    }
}
