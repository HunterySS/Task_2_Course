package com.ilya.textapp.service;

import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.entity.impl.TextComponentType;
import com.ilya.textapp.exception.TextProcessingException;
import com.ilya.textapp.service.impl.ClauseSorter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ClauseSorterService implements ClauseSorter {
    private static final Logger LOGGER = LogManager.getLogger(ClauseSorterService.class);

    @Override
    public List<TextComponent> sortClausesByLetterCount(TextComponent document, char letter) throws TextProcessingException {
        LOGGER.debug("Sorting clauses by count of letter: '{}'", letter);

        if (document == null) {
            LOGGER.error("Document is null");
            throw new TextProcessingException("Document cannot be null");
        }

        char targetLetter = Character.toLowerCase(letter);
        List<TextComponent> allSentences = extractAllByType(document, TextComponentType.SENTENCE);

        LOGGER.info("Found {} sentences to sort", allSentences.size());

        for (int i = 0; i < allSentences.size() - 1; i++) {
            for (int j = i + 1; j < allSentences.size(); j++) {
                int countI = countLetterInSentence(allSentences.get(i), targetLetter);
                int countJ = countLetterInSentence(allSentences.get(j), targetLetter);

                if (countI > countJ) {
                    TextComponent temp = allSentences.get(i);
                    allSentences.set(i, allSentences.get(j));
                    allSentences.set(j, temp);
                }
            }
        }

        return allSentences;
    }

    private List<TextComponent> extractAllByType(TextComponent component, TextComponentType targetType) {
        List<TextComponent> result = new ArrayList<>();

        if (component.getType() == targetType) {
            result.add(component);
        }

        for (TextComponent child : component.getChildren()) {
            result.addAll(extractAllByType(child, targetType));
        }

        return result;
    }

    private int countLetterInSentence(TextComponent sentence, char letter) {
        String text = sentence.restore();
        String lowerText = text.toLowerCase();
        int count = 0;

        for (int i = 0; i < lowerText.length(); i++) {
            if (lowerText.charAt(i) == letter) {
                count++;
            }
        }

        return count;
    }
}