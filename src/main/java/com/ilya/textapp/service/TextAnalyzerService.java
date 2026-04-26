package com.ilya.textapp.service;

import com.ilya.textapp.entity.Block;
import com.ilya.textapp.entity.Clause;
import com.ilya.textapp.entity.Token;
import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;
import com.ilya.textapp.service.impl.TextAnalyzer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class TextAnalyzerService implements TextAnalyzer {
    private static final Logger LOGGER = LogManager.getLogger(TextAnalyzerService.class);

    @Override
    public int findMaxSentencesWithSameWords(CompositeComponent document) throws TextProcessingException {
        LOGGER.debug("Starting analysis: find max sentences with same words");

        if (document == null) {
            LOGGER.error("Document is null");
            throw new TextProcessingException("Document cannot be null");
        }

        List<Clause> allClauses = extractAllClauses(document);
        LOGGER.info("Total clauses found: {}", allClauses.size());

        Map<String, List<Integer>> wordToSentenceIndices = new HashMap<>();

        for (int i = 0; i < allClauses.size(); i++) {
            Clause clause = allClauses.get(i);
            Set<String> uniqueWords = extractUniqueWords(clause);

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

    private List<Clause> extractAllClauses(CompositeComponent component) {
        List<Clause> result = new ArrayList<>();

        if (component instanceof Clause) {
            result.add((Clause) component);
        } else if (component instanceof Block) {
            List<CompositeComponent> children = component.getChildren();
            for (int i = 0; i < children.size(); i++) {
                result.addAll(extractAllClauses(children.get(i)));
            }
        } else if (component.getClass().getSimpleName().equals("DocumentRoot")) {
            List<CompositeComponent> children = component.getChildren();
            for (int i = 0; i < children.size(); i++) {
                result.addAll(extractAllClauses(children.get(i)));
            }
        }

        return result;
    }

    private Set<String> extractUniqueWords(Clause clause) {
        Set<String> uniqueWords = new HashSet<>();
        List<CompositeComponent> components = clause.getChildren();

        for (int i = 0; i < components.size(); i++) {
            if (components.get(i) instanceof Token) {
                Token token = (Token) components.get(i);
                String word = token.getValue().toLowerCase();
                if (word.length() > 1) {
                    uniqueWords.add(word);
                }
            }
        }

        return uniqueWords;
    }
}