package com.ilya.textapp.service.impl;

import com.ilya.textapp.entity.Clause;
import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;
import java.util.List;

public interface ClauseSorter {
    List<Clause> sortClausesByLetterCount(CompositeComponent document, char letter) throws TextProcessingException;
}