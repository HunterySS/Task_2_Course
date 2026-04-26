package com.ilya.textapp.entity;

import com.ilya.textapp.entity.impl.CompositeComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Clause implements CompositeComponent {
    private final List<CompositeComponent> components = new ArrayList<>();

    @Override
    public void add(CompositeComponent component) {
        components.add(component);
    }

    @Override
    public void remove(CompositeComponent component) {
        components.remove(component);
    }

    @Override
    public List<CompositeComponent> getChildren() {
        return components;
    }

    @Override
    public String restore() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < components.size(); i++) {
            result.append(components.get(i).restore());
            if (i < components.size() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    @Override
    public int countLetters() {
        int total = 0;
        for (int i = 0; i < components.size(); i++) {
            total += components.get(i).countLetters();
        }
        return total;
    }

    @Override
    public int countSymbols() {
        int total = 0;
        for (int i = 0; i < components.size(); i++) {
            total += components.get(i).countSymbols();
        }
        return total;
    }
}