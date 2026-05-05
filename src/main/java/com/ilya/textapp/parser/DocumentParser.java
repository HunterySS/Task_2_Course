package com.ilya.textapp.parser;

import com.ilya.textapp.entity.TextComposite;
import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.entity.impl.TextComponentType;
import com.ilya.textapp.exception.TextProcessingException;

public class DocumentParser extends AbstractTextParser {
    private static final String BLOCK_DELIMITER = "\\n\\s*\\n";

    @Override
    public TextComponent parse(String text) throws TextProcessingException {
        LOGGER.debug("Parsing document");

        if (text == null || text.strip().isBlank()) {
            LOGGER.error("Text is null or empty");
            throw new TextProcessingException("Text cannot be null or empty");
        }

        TextComposite document = new TextComposite(TextComponentType.DOCUMENT);
        String[] blocks = text.split(BLOCK_DELIMITER);
        LOGGER.info("Found {} blocks (paragraphs)", blocks.length);

        for (String blockText : blocks) {
            TextComponent block = parseNext(blockText.strip());
            if (block != null) {
                document.add(block);
            }
        }

        LOGGER.debug("Document parsed successfully. Total blocks: {}", document.getChildren().size());
        return document;
    }
}