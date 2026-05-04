package com.ilya.textapp.util.impl;

import com.ilya.textapp.exception.TextProcessingException;

public interface FileReader {
    String readFile(String filePath) throws TextProcessingException;
}