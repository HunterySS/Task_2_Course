package com.ilya.textapp.parser;

import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;
import com.ilya.textapp.parser.impl.TextParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractTextParser implements TextParser {
    protected static final Logger LOGGER = LogManager.getLogger(AbstractTextParser.class);
    protected TextParser next;

    @Override
    public void setNext(TextParser next) {
        this.next = next;
    }

    protected CompositeComponent parseNext(String text) throws TextProcessingException {
        if (next != null) {
            return next.parse(text);
        }
        LOGGER.warn("End of chain reached for text: {}", text);
        return null;
    }
}