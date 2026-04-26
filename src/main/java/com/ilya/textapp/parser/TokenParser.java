package com.ilya.textapp.parser;

import com.ilya.textapp.entity.Mark;
import com.ilya.textapp.entity.Token;
import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;

public class TokenParser extends AbstractTextParser {
    private static final String WORD_PATTERN = "^[a-zA-Zа-яА-ЯёЁ]+$";
    private static final String PUNCTUATION_PATTERN = "^[.!?;:,\\-\\\"'()\\[\\]{}<>]$";

    @Override
    public CompositeComponent parse(String text) throws TextProcessingException {
        LOGGER.trace("Parsing token: {}", text);

        if (text == null || text.isEmpty()) {
            LOGGER.warn("Empty token received");
            return null;
        }

        if (text.matches(WORD_PATTERN)) {
            LOGGER.trace("Identified as word token: {}", text);
            return new Token(text);
        } else if (text.length() == 1 && text.matches(PUNCTUATION_PATTERN)) {
            LOGGER.trace("Identified as punctuation mark: {}", text);
            return new Mark(text);
        } else {
            LOGGER.trace("Contains non-letter characters, treating as word token: {}", text);
            return new Token(text);
        }
    }
}