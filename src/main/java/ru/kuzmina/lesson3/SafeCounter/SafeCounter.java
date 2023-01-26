package ru.kuzmina.lesson3.SafeCounter;

public class SafeCounter {
    private volatile int count;

    synchronized void increment() {
        count++;
    }

    synchronized void decrement() {
        count--;
    }

    public int getCount() {
        return count;
    }
}
