package com.ilya.textapp.entity;

import com.ilya.textapp.entity.impl.CompositeComponent;
import java.util.Collections;
import java.util.List;

public class Mark implements CompositeComponent {
    private final String value;

    public Mark(String value) {
        if (value == null || value.length() != 1 || Character.isLetterOrDigit(value.charAt(0))) {
            throw new IllegalArgumentException("Mark must be a single non-letter non-digit character");
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public void add(CompositeComponent component) {
        throw new UnsupportedOperationException("Mark is leaf");
    }

    @Override
    public void remove(CompositeComponent component) {
        throw new UnsupportedOperationException("Mark is leaf");
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
        return 0;
    }

    @Override
    public int countSymbols() {
        return 1;
    }
}
