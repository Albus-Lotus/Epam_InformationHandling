package edu.kravchenko.infohandling.service;

import edu.kravchenko.infohandling.entity.InfoComponent;
import edu.kravchenko.infohandling.exception.TextException;
import edu.kravchenko.infohandling.parser.impl.SentenceParser;
import edu.kravchenko.infohandling.parser.impl.TextParser;
import edu.kravchenko.infohandling.service.impl.TestServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextServiceImpl {
    private final TextService textService = new TestServiceImpl();

    @BeforeAll
    public static void setUp() throws TextException {
    }

    @Test
    public void sortParagraphsBySentencesCount() throws TextException, IOException {
        String text1 = "\tcat, dog.\n\tcat, cat, dog. \n\tcat, dog. dog.";
        String text2 = "\tcat, dog. dog.\n\tcat, dog.\n\tcat, cat, dog.";
        InfoComponent expected = TextParser.getInstance().parse(text1);
        InfoComponent actual = TextParser.getInstance().parse(text2);
        textService.sortParagraphsBySentencesCount(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void findSentencesWithLongestWord() throws TextException {
        String text1 = "\tcats, dogs.\n\tcat, cat, dogs. \n\tcats, dog.";
        String text2 = "\tcats, dogs. cat.\n\tcat, cat, dogs. \n\tcats, dog. dog.";
        List<InfoComponent> expected = TextParser.getInstance().parse(text1).getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .collect(Collectors.toList());
        InfoComponent actualTree = TextParser.getInstance().parse(text2);
        List<InfoComponent> actual = textService.findSentencesWithLongestWord(actualTree);
        assertEquals(expected, actual);
    }

    @Test
    public void deleteSentencesWithWordCountLessGiven() throws TextException {
        String text1 = "\tcat, cat, dogs.";
        String text2 = "\tcats, dogs. cat. cat, cat, dogs. cats, dog. dog.";
        InfoComponent expected = TextParser.getInstance().parse(text1);
        InfoComponent actual = TextParser.getInstance().parse(text2);
        textService.deleteSentencesWithWordCountLessGiven(actual, 3);
        assertEquals(expected, actual);
    }

    @Test
    public void countEqualWords() throws TextException {
        int expected = 3;
        String text = "\tcats, dogs. cat. cat, cat, dogs. cats, dog. dog.";
        InfoComponent actualTree = TextParser.getInstance().parse(text);
        int actual = textService.countEqualWords(actualTree, "cat");
        assertEquals(expected, actual);
    }

    @Test
    public void countConsonants() throws TextException {
        int expected = 5;
        String text = "cats, doegs, serty.";
        InfoComponent actualTree = SentenceParser.getInstance().parse(text);
        int actual = textService.countConsonants(actualTree);
        assertEquals(expected, actual);
    }

    @Test
    public void countVowels() throws TextException {
        int expected = 9;
        String text = "cats, doegs, serty.";
        InfoComponent actualTree = SentenceParser.getInstance().parse(text);
        int actual = textService.countVowels(actualTree);
        assertEquals(expected, actual);
    }
}
