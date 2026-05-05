package com.ilya.textapp.service.impl;

import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.exception.TextProcessingException;
import java.util.List;

public interface ClauseSorter {
    List<TextComponent> sortClausesByLetterCount(TextComponent document, char letter) throws TextProcessingException;
}