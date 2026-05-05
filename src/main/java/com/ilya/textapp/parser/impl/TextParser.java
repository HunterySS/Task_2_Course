package com.ilya.textapp.parser.impl;

import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.exception.TextProcessingException;

public interface TextParser {
    TextComponent parse(String text) throws TextProcessingException;
    void setNext(TextParser next);
}