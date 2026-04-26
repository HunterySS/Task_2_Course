package com.ilya.textapp.service.impl;

import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;

public interface TokenSwapper {
    CompositeComponent swapFirstAndLastToken(CompositeComponent document) throws TextProcessingException;
}