package ru.kuzmina.lesson3.SafeCounter;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeCounter {
    private int count;
    Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
            System.out.println(Thread.currentThread().getName() + "(increment): " + count);
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            count--;
            System.out.println(Thread.currentThread().getName() + "(decrement): " + count);
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        return count;
    }
}
