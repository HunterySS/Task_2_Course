package com.ilya.textapp.entity;

import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.entity.impl.TextComponentType;
import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private final List<TextComponent> children = new ArrayList<>();
    private final TextComponentType type;

    public TextComposite(TextComponentType type) {
        this.type = type;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public void add(TextComponent component) {
        children.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        children.remove(component);
    }

    @Override
    public List<TextComponent> getChildren() {
        return children;
    }

    @Override
    public String restore() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < children.size(); i++) {
            TextComponent child = children.get(i);
            String childText = child.restore();

            result.append(childText);

            if (type == TextComponentType.DOCUMENT) {
                if (i < children.size() - 1) {
                    result.append("\n");
                }
            } else if (type == TextComponentType.PARAGRAPH) {
                if (i < children.size() - 1) {
                    result.append(" ");
                }
            } else if (type == TextComponentType.SENTENCE) {
                if (i < children.size() - 1) {
                    TextComponent next = children.get(i + 1);
                    boolean isCurrentWord = (child.getType() == TextComponentType.WORD);
                    boolean isNextWord = (next.getType() == TextComponentType.WORD);
                    boolean isCurrentPunctuation = (child.getType() == TextComponentType.PUNCTUATION);

                    if (isCurrentWord && isNextWord) {
                        result.append(" ");
                    } else if (isCurrentPunctuation && isNextWord) {
                        result.append(" ");
                    }
                }
            }
        }

        return result.toString();
    }

    @Override
    public int countLetters() {
        int total = 0;
        for (TextComponent child : children) {
            total += child.countLetters();
        }
        return total;
    }

    @Override
    public int countSymbols() {
        int total = 0;
        for (TextComponent child : children) {
            total += child.countSymbols();
        }
        return total;
    }
}