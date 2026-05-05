package com.ilya.textapp.entity.impl;

import java.util.List;

public interface TextComponent {
    void add(TextComponent component);
    void remove(TextComponent component);
    List<TextComponent> getChildren();
    String restore();
    int countLetters();
    int countSymbols();
    TextComponentType getType();
}