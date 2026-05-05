package com.ilya.textapp.parser;

import com.ilya.textapp.entity.TextLeaf;
import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.entity.impl.TextComponentType;
import com.ilya.textapp.exception.TextProcessingException;

public class TokenParser extends AbstractTextParser {
    private static final String WORD_PATTERN = "^[a-zA-Zа-яА-ЯёЁ]+$";
    private static final String PUNCTUATION_PATTERN = "^[.!?;:,\\-\\\"'()\\[\\]{}<>]$";

    @Override
    public TextComponent parse(String text) throws TextProcessingException {
        LOGGER.trace("Parsing token: {}", text);

        if (text == null || text.isEmpty()) {
            LOGGER.warn("Empty token received");
            return null;
        }

        if (text.matches(WORD_PATTERN)) {
            LOGGER.trace("Identified as word: {}", text);
            return new TextLeaf(text, TextComponentType.WORD);
        } else if (text.length() == 1 && text.matches(PUNCTUATION_PATTERN)) {
            LOGGER.trace("Identified as punctuation: {}", text);
            return new TextLeaf(text, TextComponentType.PUNCTUATION);
        } else {
            LOGGER.trace("Contains non-letter characters, treating as word: {}", text);
            return new TextLeaf(text, TextComponentType.WORD);
        }
    }
}