package com.ilya.textapp.service;

import com.ilya.textapp.entity.TextComposite;
import com.ilya.textapp.entity.TextLeaf;
import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.entity.impl.TextComponentType;
import com.ilya.textapp.exception.TextProcessingException;
import com.ilya.textapp.service.impl.TextAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TextAnalyzerService implements TextAnalyzer {
    private static final Logger LOGGER = LogManager.getLogger(TextAnalyzerService.class);

    @Override
    public int findMaxSentencesWithSameWords(TextComponent document) throws TextProcessingException {
        LOGGER.debug("Starting analysis: find max sentences with same words");

        if (document == null) {
            LOGGER.error("Document is null");
            throw new TextProcessingException("Document cannot be null");
        }

        List<TextComponent> allSentences = extractAllByType(document, TextComponentType.SENTENCE);
        LOGGER.info("Total sentences found: {}", allSentences.size());

        Map<String, List<Integer>> wordToSentenceIndices = new HashMap<>();

        for (int i = 0; i < allSentences.size(); i++) {
            Set<String> uniqueWords = extractUniqueWords(allSentences.get(i));

            for (String word : uniqueWords) {
                if (wordToSentenceIndices.containsKey(word)) {
                    wordToSentenceIndices.get(word).add(i);
                } else {
                    List<Integer> newList = new ArrayList<>();
                    newList.add(i);
                    wordToSentenceIndices.put(word, newList);
                }
            }
        }

        int maxSentences = 0;
        for (List<Integer> indices : wordToSentenceIndices.values()) {
            if (indices.size() > maxSentences) {
                maxSentences = indices.size();
            }
        }

        LOGGER.info("Maximum number of sentences containing same word: {}", maxSentences);
        return maxSentences;
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

    private Set<String> extractUniqueWords(TextComponent sentence) {
        Set<String> uniqueWords = new HashSet<>();

        for (TextComponent child : sentence.getChildren()) {
            if (child.getType() == TextComponentType.WORD) {
                TextLeaf wordLeaf = (TextLeaf) child;
                String word = wordLeaf.getValue().toLowerCase();
                if (word.length() > 1) {
                    uniqueWords.add(word);
                }
            }
        }

        return uniqueWords;
    }
}