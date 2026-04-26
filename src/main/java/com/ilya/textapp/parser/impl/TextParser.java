package com.ilya.textapp.parser.impl;

import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;

public interface TextParser {
    CompositeComponent parse(String text) throws TextProcessingException;
    void setNext(TextParser next);
}