package com.ilya.textapp.parser;

import com.ilya.textapp.entity.Clause;
import com.ilya.textapp.entity.impl.CompositeComponent;
import com.ilya.textapp.exception.TextProcessingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClauseParser extends AbstractTextParser {
    private static final Pattern TOKEN_PATTERN = Pattern.compile("([a-zA-Zа-яА-ЯёЁ]+|[^a-zA-Zа-яА-ЯёЁ\\s]+)");

    @Override
    public CompositeComponent parse(String text) throws TextProcessingException {
        LOGGER.debug("Parsing clause: {}", text.length() > 50 ? text.substring(0, 50) + "..." : text);

        Clause clause = new Clause();
        Matcher matcher = TOKEN_PATTERN.matcher(text);

        int tokenCount = 0;
        while (matcher.find()) {
            String token = matcher.group(1);
            CompositeComponent component = parseNext(token);
            if (component != null) {
                clause.add(component);
                tokenCount++;
            }
        }

        LOGGER.info("Clause parsed. Tokens found: {}", tokenCount);
        return clause;
    }
}