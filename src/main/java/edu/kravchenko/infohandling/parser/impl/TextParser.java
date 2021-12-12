package edu.kravchenko.infohandling.parser.impl;

import edu.kravchenko.infohandling.entity.ComponentType;
import edu.kravchenko.infohandling.entity.InfoComponent;
import edu.kravchenko.infohandling.entity.TextComposite;
import edu.kravchenko.infohandling.exception.TextException;
import edu.kravchenko.infohandling.parser.InfoParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextParser implements InfoParser {
    private static final Logger logger = LogManager.getLogger();
    private static final TextParser INSTANCE = new TextParser();
    private final String TEXT_SPLIT_REGEXP = "\\n+\\s*";
    private InfoParser nextParser = ParagraphParser.getInstance();

    private TextParser() {
    }

    public static TextParser getInstance() {
        return INSTANCE;
    }

    @Override
    public InfoComponent parse(String text) throws TextException {
        if (nextParser == null) {
            throw new TextException("Paragraph parser is not specified");
        }
        String[] paragraphs = text.strip().split(TEXT_SPLIT_REGEXP);
        var component = new TextComposite(ComponentType.TEXT);
        InfoComponent paragraphComponent;
        for (String paragraph : paragraphs) {
            paragraphComponent = nextParser.parse(paragraph);
            component.add(paragraphComponent);
        }
        logger.log(Level.INFO, "text were successfully parsed: {}", text);
        return component;
    }
}
