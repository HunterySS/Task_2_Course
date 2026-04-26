package com.ilya.textapp.entity.impl;

import java.util.List;

public interface CompositeComponent {
    void add(CompositeComponent component);
    void remove(CompositeComponent component);
    List<CompositeComponent> getChildren();
    String restore();
    int countLetters();
    int countSymbols();
}