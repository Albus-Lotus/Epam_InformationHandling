package edu.kravchenko.infohandling.service.impl;

import edu.kravchenko.infohandling.entity.InfoComponent;
import edu.kravchenko.infohandling.exception.TextException;
import edu.kravchenko.infohandling.service.TextService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static edu.kravchenko.infohandling.entity.ComponentType.*;

public class TestServiceImpl implements TextService {
    private final String VOWEL_REGEXP = "[a-zA-Z&&[^EUIOAYeuioay]]";
    private final String CONSONANT_REGEXP = "[EUIOAYeuioay]";

    @Override
    public void sortParagraphsBySentencesCount(InfoComponent component) throws TextException {
        if (component.getType() != TEXT) {
            throw new TextException("Incorrect component type (expected TEXT): " + component.getType());
        }
        Comparator<InfoComponent> sentenceCountComparator = (o1, o2) -> {
            int count1 = o1.getChildren().size();
            int count2 = o2.getChildren().size();
            return Integer.compare(count1, count2);
        };
        List<InfoComponent> paragraphs = component.getChildren();
        paragraphs.sort(sentenceCountComparator);
    }

    @Override
    public List<InfoComponent> findSentencesWithLongestWord(InfoComponent component) throws TextException {
        if (component.getType() != TEXT) {
            throw new TextException("Incorrect component type (expected TEXT): " + component.getType());
        }
        Optional<Integer> maxLength = component.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .map(this::countMaxLengthWord)
                .max(Integer::compareTo);
        if (maxLength.isEmpty()) {
            throw new TextException("Text contains no words");
        }
        return component.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .filter(s -> countMaxLengthWord(s) == maxLength.get())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSentencesWithWordCountLessGiven(InfoComponent component, int wordCount) throws TextException {
        if (component.getType() != TEXT) {
            throw new TextException("Incorrect component type (expected TEXT): " + component.getType());
        }
        List<InfoComponent> paragraphs = component.getChildren();
        List<InfoComponent> sentences;
        for (InfoComponent paragraph : paragraphs) {
            sentences = paragraph.getChildren();
            sentences.removeIf(s -> countWords(s) < wordCount);
        }
    }

    @Override
    public int countEqualWords(InfoComponent component, String word) throws TextException {
        if (component.getType() != TEXT) {
            throw new TextException("Incorrect component type (expected TEXT): " + component.getType());
        }
        return (int) component.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(o -> o.getType() == WORD)
                .filter(w -> w.toString().equals(word))
                .count();
    }

    @Override
    public int countConsonants(InfoComponent component) throws TextException {
        if (component.getType() != SENTENCE) {
            throw new TextException("Incorrect component type (expected SENTENCE): " + component.getType());
        }
        return countSymbols(component, CONSONANT_REGEXP);
    }

    @Override
    public int countVowels(InfoComponent component) throws TextException {
        if (component.getType() != SENTENCE) {
            throw new TextException("Incorrect component type (expected SENTENCE): " + component.getType());
        }
        return countSymbols(component, VOWEL_REGEXP);
    }

    private int countMaxLengthWord(InfoComponent sentence) {
        return sentence.getChildren().stream()
                .flatMap(l -> l.getChildren().stream())
                .filter(o -> o.getType() == WORD)
                .map(w -> w.getChildren().size())
                .max(Integer::compareTo)
                .orElse(-1);
    }

    private int countWords(InfoComponent sentence) {
        List<InfoComponent> lexemes = sentence.getChildren();
        List<InfoComponent> words;
        int count = 0;
        for (InfoComponent lexeme : lexemes) {
            words = lexeme.getChildren();
            for (InfoComponent word : words) {
                if (word.getType() == WORD) {
                    count++;
                }
            }
        }
        return count;
    }

    private int countSymbols(InfoComponent component, String regexp) {
        return (int) component.getChildren().stream()
                .flatMap(l -> l.getChildren().stream())
                .filter(o -> o.getType() == WORD)
                .flatMap(w -> w.getChildren().stream())
                .filter(s -> s.toString().matches(regexp))
                .count();
    }
}
