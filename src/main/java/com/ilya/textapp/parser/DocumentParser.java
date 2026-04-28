package com.ilya.textapp.parser;

import com.ilya.textapp.entity.DocumentRoot;
import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;

public class DocumentParser extends AbstractTextParser {
    private static final String BLOCK_DELIMITER = "\\n\\s*\\n";

    @Override
    public CompositeComponent parse(String text) throws TextProcessingException {
        LOGGER.debug("Parsing document");

        if (text == null || text.strip().isBlank()) {
            LOGGER.error("Text is null or empty");
            throw new TextProcessingException("Text cannot be null or empty");
        }

        DocumentRoot root = new DocumentRoot();
        String[] blocks = text.split(BLOCK_DELIMITER);
        LOGGER.info("Found {} blocks (paragraphs)", blocks.length);

        for (String blockText : blocks) {
            CompositeComponent block = parseNext(blockText.strip());
            if (block != null) {
                root.add(block);
            }
        }

        LOGGER.debug("Document parsed successfully. Total blocks: {}", root.getChildren().size());
        return root;
    }
}