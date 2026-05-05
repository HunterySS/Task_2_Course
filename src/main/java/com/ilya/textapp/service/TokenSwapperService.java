package com.ilya.textapp.service;

import com.ilya.textapp.entity.TextComposite;
import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.entity.impl.TextComponentType;
import com.ilya.textapp.exception.TextProcessingException;
import com.ilya.textapp.service.impl.TokenSwapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TokenSwapperService implements TokenSwapper {
    private static final Logger LOGGER = LogManager.getLogger(TokenSwapperService.class);

    @Override
    public TextComponent swapFirstAndLastToken(TextComponent document) throws TextProcessingException {
        LOGGER.debug("Swapping first and last token in each sentence");

        if (document == null) {
            LOGGER.error("Document is null");
            throw new TextProcessingException("Document cannot be null");
        }

        processComponent(document);
        LOGGER.info("Token swapping completed");

        return document;
    }

    private void processComponent(TextComponent component) {
        if (component.getType() == TextComponentType.SENTENCE) {
            swapTokensInSentence((TextComposite) component);
        }

        for (TextComponent child : component.getChildren()) {
            processComponent(child);
        }
    }

    private void swapTokensInSentence(TextComposite sentence) {
        List<TextComponent> components = sentence.getChildren();

        int firstWordIndex = -1;
        int lastWordIndex = -1;

        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).getType() == TextComponentType.WORD) {
                if (firstWordIndex == -1) {
                    firstWordIndex = i;
                }
                lastWordIndex = i;
            }
        }

        if (firstWordIndex != -1 && lastWordIndex != -1 && firstWordIndex != lastWordIndex) {
            TextComponent first = components.get(firstWordIndex);
            TextComponent last = components.get(lastWordIndex);

            components.set(firstWordIndex, last);
            components.set(lastWordIndex, first);

            LOGGER.debug("Swapped first word '{}' with last word '{}'", first.restore(), last.restore());
        }
    }
}