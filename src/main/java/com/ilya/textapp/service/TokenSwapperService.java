package com.ilya.textapp.service;

import com.ilya.textapp.entity.Block;
import com.ilya.textapp.entity.Clause;
import com.ilya.textapp.entity.Mark;
import com.ilya.textapp.entity.Token;
import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;
import com.ilya.textapp.service.impl.TokenSwapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TokenSwapperService implements TokenSwapper {
    private static final Logger LOGGER = LogManager.getLogger(TokenSwapperService.class);

    @Override
    public CompositeComponent swapFirstAndLastToken(CompositeComponent document) throws TextProcessingException {
        LOGGER.debug("Swapping first and last token in each clause");

        if (document == null) {
            LOGGER.error("Document is null");
            throw new TextProcessingException("Document cannot be null");
        }

        processComponent(document);
        LOGGER.info("Token swapping completed");

        return document;
    }

    private void processComponent(CompositeComponent component) {
        if (component instanceof Clause) {
            swapTokensInClause((Clause) component);
        } else if (component instanceof Block) {
            List<CompositeComponent> children = component.getChildren();
            for (int i = 0; i < children.size(); i++) {
                processComponent(children.get(i));
            }
        } else if (component.getClass().getSimpleName().equals("DocumentRoot")) {
            List<CompositeComponent> children = component.getChildren();
            for (int i = 0; i < children.size(); i++) {
                processComponent(children.get(i));
            }
        }
    }

    private void swapTokensInClause(Clause clause) {
        List<CompositeComponent> components = clause.getChildren();

        int firstTokenIndex = -1;
        int lastTokenIndex = -1;

        for (int i = 0; i < components.size(); i++) {
            if (components.get(i) instanceof Token) {
                if (firstTokenIndex == -1) {
                    firstTokenIndex = i;
                }
                lastTokenIndex = i;
            }
        }

        if (firstTokenIndex != -1 && lastTokenIndex != -1 && firstTokenIndex != lastTokenIndex) {
            CompositeComponent first = components.get(firstTokenIndex);
            CompositeComponent last = components.get(lastTokenIndex);

            components.set(firstTokenIndex, last);
            components.set(lastTokenIndex, first);

            LOGGER.debug("Swapped first word '{}' with last word '{}'", first.restore(), last.restore());
        }
    }
}