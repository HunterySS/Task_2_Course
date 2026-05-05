package com.ilya.textapp.parser;

import com.ilya.textapp.entity.TextComposite;
import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.entity.impl.TextComponentType;
import com.ilya.textapp.exception.TextProcessingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClauseParser extends AbstractTextParser {
    private static final Pattern TOKEN_PATTERN = Pattern.compile("([a-zA-Zа-яА-ЯёЁ]+|[^a-zA-Zа-яА-ЯёЁ\\s]+)");

    @Override
    public TextComponent parse(String text) throws TextProcessingException {
        LOGGER.debug("Parsing clause: {}", text.length() > 50 ? text.substring(0, 50) + "..." : text);

        TextComposite sentence = new TextComposite(TextComponentType.SENTENCE);
        Matcher matcher = TOKEN_PATTERN.matcher(text);

        int tokenCount = 0;
        while (matcher.find()) {
            String token = matcher.group(1);
            TextComponent component = parseNext(token);
            if (component != null) {
                sentence.add(component);
                tokenCount++;
            }
        }

        LOGGER.info("Clause parsed. Tokens found: {}", tokenCount);
        return sentence;
    }
}