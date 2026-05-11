package com.ilya.textapp.entity;

import com.ilya.textapp.entity.impl.TextComponent;
import com.ilya.textapp.entity.impl.TextComponentType;
import java.util.Collections;
import java.util.List;

public class TextLeaf implements TextComponent {
    private final String value;
    private final TextComponentType type;

    public TextLeaf(String value, TextComponentType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public TextComponentType getType() {
        return type;
    }

    @Override
    public void add(TextComponent component) {
        throw new UnsupportedOperationException("Leaf cannot add children");
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException("Leaf cannot remove children");
    }

    @Override
    public List<TextComponent> getChildren() {
        throw new UnsupportedOperationException(" Leaf has no children");
    }

    @Override
    public String restore() {
        return value;
    }

    @Override
    public int countLetters() {
        if (type == TextComponentType.PUNCTUATION) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < value.length(); i++) {
            if (Character.isLetter(value.charAt(i))) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countSymbols() {
        return value.length();
    }
}