package edu.kravchenko.infohandling.parser.impl;

import edu.kravchenko.infohandling.entity.ComponentType;
import edu.kravchenko.infohandling.entity.InfoComponent;
import edu.kravchenko.infohandling.entity.TextComposite;
import edu.kravchenko.infohandling.exception.TextException;
import edu.kravchenko.infohandling.parser.InfoParser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ParagraphParser implements InfoParser {
    private static final Logger logger = LogManager.getLogger();
    private static final ParagraphParser INSTANCE = new ParagraphParser();
    private final String PARAGRAPH_SPLIT_REGEXP = "(?<=((\\.)|((\\.){3})|[?!]))\\s+";
    private InfoParser nextParser = SentenceParser.getInstance();

    private ParagraphParser() {
    }

    public static ParagraphParser getInstance() {
        return INSTANCE;
    }

    @Override
    public InfoComponent parse(String text) throws TextException {
        String[] sentences = text.strip().split(PARAGRAPH_SPLIT_REGEXP);
        var component = new TextComposite(ComponentType.PARAGRAPH);
        InfoComponent sentenceComponent;
        for (String sentence : sentences) {
            sentenceComponent = nextParser.parse(sentence);
            component.add(sentenceComponent);
        }
        logger.log(Level.INFO, "paragraph were successfully parsed: {}", text);
        return component;
    }
}
