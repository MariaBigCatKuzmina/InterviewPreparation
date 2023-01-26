package ru.kuzmina.lesson2.ArrayList;

public enum Shift {
    LEFT(0),
    RIGHT(-1);

    private final int value;

    Shift(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
