package ru.kuzmina.lesson2.ArrayList;

import java.util.Arrays;

public class MyArrayList<E> {
    private static final int INITIAL_SIZE = 16;

    private Object[] list;
    private int size;

    public MyArrayList() {
        list = new Object[INITIAL_SIZE];
    }

    public void add(E value) {
        add(value, size);
    }

    public void add(E value, int index) {
        if (size == list.length) {
            list = resize();
        }
        if (index < size) {
            shiftPart(index, Shift.LEFT);
        }
        list[index] = value;
        size++;

    }

    public void remove(int index) {
        if (index < size) {
            shiftPart(index, Shift.RIGHT);
            size--;
        }
    }

    public E get(int index) {
        if (index < size) {
            return (E) list[index];
        }
        return null;
    }

    public int indexOf(E value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (list[i] == null) {
                    return i;
                }
            }

        } else {
            for (int i = 0; i < size; i++) {
                if (list[i].equals(value)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void shiftPart(int index, Shift shift) {
        int shiftValue = shift.getValue();
        System.arraycopy(list, index - shiftValue, list, index + 1 + shiftValue, size - index + shiftValue);
    }


    public boolean isEmpty() {
        return size == 0;
    }

    private Object[] resize() {
        return Arrays.copyOf(list, list.length + list.length / 2);
    }

    private Object[] actualArray() {
        return Arrays.copyOf(list, size);
    }

    @Override
    public String toString() {
        return Arrays.toString(actualArray());
    }
}
