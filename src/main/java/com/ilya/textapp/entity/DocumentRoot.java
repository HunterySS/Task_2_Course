package com.ilya.textapp.entity;

import com.ilya.textapp.entity.impl.CompositeComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentRoot implements CompositeComponent {
    private final List<CompositeComponent> blocks = new ArrayList<>();

    @Override
    public void add(CompositeComponent component) {
        blocks.add(component);
    }

    @Override
    public void remove(CompositeComponent component) {
        blocks.remove(component);
    }

    @Override
    public List<CompositeComponent> getChildren() {
        return new ArrayList<>(blocks);
    }

    @Override
    public String restore() {
        return blocks.stream()
                .map(CompositeComponent::restore)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public int countLetters() {
        return blocks.stream().mapToInt(CompositeComponent::countLetters).sum();
    }

    @Override
    public int countSymbols() {
        return blocks.stream().mapToInt(CompositeComponent::countSymbols).sum();
    }
}