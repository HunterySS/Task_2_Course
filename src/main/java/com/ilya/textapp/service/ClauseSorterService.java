package com.ilya.textapp.service;

import com.ilya.textapp.entity.Block;
import com.ilya.textapp.entity.Clause;
import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;
import com.ilya.textapp.service.impl.ClauseSorter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class ClauseSorterService implements ClauseSorter {
    private static final Logger LOGGER = LogManager.getLogger(ClauseSorterService.class);

    @Override
    public List<Clause> sortClausesByLetterCount(CompositeComponent document, char letter) throws TextProcessingException {
        LOGGER.debug("Sorting clauses by count of letter: '{}'", letter);

        if (document == null) {
            LOGGER.error("Document is null");
            throw new TextProcessingException("Document cannot be null");
        }

        char targetLetter = Character.toLowerCase(letter);
        List<Clause> allClauses = extractAllClauses(document);

        LOGGER.info("Found {} clauses to sort", allClauses.size());

        for (int i = 0; i < allClauses.size() - 1; i++) {
            for (int j = i + 1; j < allClauses.size(); j++) {
                int countI = countLetterInClause(allClauses.get(i), targetLetter);
                int countJ = countLetterInClause(allClauses.get(j), targetLetter);

                if (countI > countJ) {
                    Clause temp = allClauses.get(i);
                    allClauses.set(i, allClauses.get(j));
                    allClauses.set(j, temp);
                }
            }
        }

        for (int i = 0; i < allClauses.size(); i++) {
            int count = countLetterInClause(allClauses.get(i), targetLetter);
            String preview = allClauses.get(i).restore();
            if (preview.length() > 50) {
                preview = preview.substring(0, 50);
            }
            LOGGER.trace("Clause has {} occurrences of '{}': {}", count, letter, preview);
        }

        return allClauses;
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

    private int countLetterInClause(Clause clause, char letter) {
        String text = clause.restore();
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