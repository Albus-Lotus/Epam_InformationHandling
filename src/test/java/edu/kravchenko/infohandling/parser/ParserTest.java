package edu.kravchenko.infohandling.parser;

import edu.kravchenko.infohandling.entity.ComponentType;
import edu.kravchenko.infohandling.entity.InfoComponent;
import edu.kravchenko.infohandling.entity.TextComposite;
import edu.kravchenko.infohandling.entity.TextSymbol;
import edu.kravchenko.infohandling.exception.TextException;
import edu.kravchenko.infohandling.parser.impl.TextParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static edu.kravchenko.infohandling.entity.ComponentType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static InfoComponent textComponent;

    @BeforeAll
    public static void setUp() throws TextException {
        textComponent = new TextComposite(ComponentType.TEXT);
        InfoComponent paragraph1 = new TextComposite(PARAGRAPH);
        InfoComponent paragraph2 = new TextComposite(PARAGRAPH);
        InfoComponent paragraph3 = new TextComposite(PARAGRAPH);
        InfoComponent sentence1 = new TextComposite(SENTENCE);
        InfoComponent sentence2 = new TextComposite(SENTENCE);
        InfoComponent sentence3 = new TextComposite(SENTENCE);
        InfoComponent lexeme1 = new TextComposite(LEXEME);
        InfoComponent lexeme2 = new TextComposite(LEXEME);
        InfoComponent word1 = new TextComposite(WORD);
        InfoComponent word2 = new TextComposite(WORD);
        word1.add(new TextSymbol(SYMBOL, 'c'));
        word1.add(new TextSymbol(SYMBOL, 'a'));
        word1.add(new TextSymbol(SYMBOL, 't'));
        word2.add(new TextSymbol(SYMBOL, 'd'));
        word2.add(new TextSymbol(SYMBOL, 'o'));
        word2.add(new TextSymbol(SYMBOL, 'g'));
        lexeme1.add(word1);
        lexeme1.add(new TextSymbol(PUNCTUATION, ','));
        lexeme2.add(word2);
        lexeme2.add(new TextSymbol(PUNCTUATION, '.'));
        sentence1.add(lexeme2);
        sentence2.add(lexeme1);
        sentence2.add(lexeme2);
        sentence3.add(lexeme1);
        sentence3.add(lexeme1);
        sentence3.add(lexeme2);
        paragraph1.add(sentence2);
        paragraph1.add(sentence1);
        paragraph2.add(sentence2);
        paragraph3.add(sentence3);
        textComponent.add(paragraph1);
        textComponent.add(paragraph2);
        textComponent.add(paragraph3);
    }

    @Test
    public void parseText() throws TextException, IOException {
        File file = new File(getClass().getClassLoader().getResource("files/text.txt").getFile());
        String filePath = file.getAbsolutePath();
        Path path = Paths.get(filePath);
        String text = Files.readString(path);
        InfoComponent expected = textComponent;
        InfoComponent actual = TextParser.getInstance().parse(text);
        assertEquals(expected, actual);
    }
}
