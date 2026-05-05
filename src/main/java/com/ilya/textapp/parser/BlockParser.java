package com.ilya.textapp.parser;

import com.ilya.textapp.entity.TextComposite;
import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.entity.impl.TextComponentType;
import com.ilya.textapp.exception.TextProcessingException;

public class BlockParser extends AbstractTextParser {
    private static final String CLAUSE_DELIMITER = "(?<=[.!?])\\s+(?=[A-Z])";

    @Override
    public TextComponent parse(String text) throws TextProcessingException {
        LOGGER.debug("Parsing block: {}", text.length() > 50 ? text.substring(0, 50) + "..." : text);

        TextComposite paragraph = new TextComposite(TextComponentType.PARAGRAPH);
        String[] clauses = text.split(CLAUSE_DELIMITER);
        LOGGER.info("Found {} clauses (sentences) in block", clauses.length);

        for (String clauseText : clauses) {
            TextComponent clause = parseNext(clauseText.trim());
            if (clause != null) {
                paragraph.add(clause);
            }
        }

        return paragraph;
    }
}