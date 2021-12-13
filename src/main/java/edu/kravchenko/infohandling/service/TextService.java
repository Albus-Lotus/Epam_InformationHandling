package edu.kravchenko.infohandling.service;

import edu.kravchenko.infohandling.entity.InfoComponent;
import edu.kravchenko.infohandling.exception.TextException;

import java.util.List;
import java.util.Map;

public interface TextService {
    void sortParagraphsBySentencesCount(InfoComponent component) throws TextException;

    List<InfoComponent> findSentencesWithLongestWord(InfoComponent component) throws TextException;

    void deleteSentencesWithWordCountLessGiven(InfoComponent component, int wordCount) throws TextException;

    Map<InfoComponent, Long> countEqualWords(InfoComponent component) throws TextException;

    int countConsonants(InfoComponent component) throws TextException;

    int countVowels(InfoComponent component) throws TextException;
}
