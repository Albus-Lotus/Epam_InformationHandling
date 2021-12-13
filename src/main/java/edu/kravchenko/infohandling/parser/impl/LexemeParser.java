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

public class LexemeParser implements InfoParser {
    private static final Logger logger = LogManager.getLogger();
    private static final LexemeParser INSTANCE = new LexemeParser();
    private static String LEXEME_SPLIT_REGEXP = "(?=[-:,)(.}{])";
    private final String WORD_REGEXP = "\\w+";
    private final String PUNCTUATION_REGEXP = "[-:,)(.}{]";
    private InfoParser nextParser = WordParser.getInstance();

    private LexemeParser() {
    }

    public static LexemeParser getInstance() {
        return INSTANCE;
    }

    @Override
    public InfoComponent parse(String text) throws TextException {
        if (nextParser == null) {
            throw new TextException("Word parser is not specified");
        }
        String[] lexemes = text.strip().split(LEXEME_SPLIT_REGEXP);
        var component = new TextComposite(ComponentType.LEXEME);
        InfoComponent lexemeComponent;
        for (String lexeme : lexemes) {
            if (lexeme.matches(WORD_REGEXP)) {
                lexemeComponent = nextParser.parse(lexeme);
            } else {
                lexemeComponent = new TextSymbol(ComponentType.PUNCTUATION, lexeme.charAt(0));
            }
            component.add(lexemeComponent);
        }
        logger.log(Level.INFO, "lexeme were successfully parsed: {}", text);
        return component;
    }
}
