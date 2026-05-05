package com.ilya.textapp.service.impl;

import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.exception.TextProcessingException;

public interface TextAnalyzer {
    int findMaxSentencesWithSameWords(TextComponent document) throws TextProcessingException;
}