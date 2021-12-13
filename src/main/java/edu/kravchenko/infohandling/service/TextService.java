package edu.kravchenko.infohandling.service;

import edu.kravchenko.infohandling.entity.InfoComponent;
import edu.kravchenko.infohandling.exception.TextException;

import java.util.List;

public interface TextService {
    void sortParagraphsBySentencesCount(InfoComponent component) throws TextException;

    List<InfoComponent> findSentencesWithLongestWord(InfoComponent component) throws TextException;

    void deleteSentencesWithWordCountLessGiven(InfoComponent component, int wordCount) throws TextException;

    int countEqualWords(InfoComponent component, String word) throws TextException;

    int countConsonants(InfoComponent component) throws TextException;

    int countVowels(InfoComponent component) throws TextException;
}
