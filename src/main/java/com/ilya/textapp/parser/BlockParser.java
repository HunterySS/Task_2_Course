package com.ilya.textapp.parser;

import com.ilya.textapp.entity.Block;
import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;

public class BlockParser extends AbstractTextParser {
    private static final String CLAUSE_DELIMITER = "(?<=[.!?])\\s+(?=[A-Z])";

    @Override
    public CompositeComponent parse(String text) throws TextProcessingException {
        LOGGER.debug("Parsing block: {}", text.length() > 50 ? text.substring(0, 50) + "..." : text);

        Block block = new Block();
        String[] clauses = text.split(CLAUSE_DELIMITER);
        LOGGER.info("Found {} clauses (sentences) in block", clauses.length);

        for (String clauseText : clauses) {
            CompositeComponent clause = parseNext(clauseText.trim());
            if (clause != null) {
                block.add(clause);
            }
        }

        return block;
    }
}