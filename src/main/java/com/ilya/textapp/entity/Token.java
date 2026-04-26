package com.ilya.textapp.entity;

import com.ilya.textapp.entity.impl.CompositeComponent;
import java.util.Collections;
import java.util.List;

public class Token implements CompositeComponent {
    private final String value;

    public Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void add(CompositeComponent component) {
        throw new UnsupportedOperationException("Token is leaf");
    }

    @Override
    public void remove(CompositeComponent component) {
        throw new UnsupportedOperationException("Token is leaf");
    }

    @Override
    public List<CompositeComponent> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String restore() {
        return value;
    }

    @Override
    public int countLetters() {
        return (int) value.chars().filter(Character::isLetter).count();
    }

    @Override
    public int countSymbols() {
        return value.length();
    }
}