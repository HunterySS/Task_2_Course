package com.ilya.textapp.entity;

import com.ilya.textapp.entity.impl.CompositeComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Block implements CompositeComponent {
    private final List<CompositeComponent> clauses = new ArrayList<>();

    @Override
    public void add(CompositeComponent component) {
        clauses.add(component);
    }

    @Override
    public void remove(CompositeComponent component) {
        clauses.remove(component);
    }

    @Override
    public List<CompositeComponent> getChildren() {
        return new ArrayList<>(clauses);
    }

    @Override
    public String restore() {
        return clauses.stream()
                .map(CompositeComponent::restore)
                .collect(Collectors.joining(" "));
    }

    @Override
    public int countLetters() {
        return clauses.stream().mapToInt(CompositeComponent::countLetters).sum();
    }

    @Override
    public int countSymbols() {
        return clauses.stream().mapToInt(CompositeComponent::countSymbols).sum();
    }
}
