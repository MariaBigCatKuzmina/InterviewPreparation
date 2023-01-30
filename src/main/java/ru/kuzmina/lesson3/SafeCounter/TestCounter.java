package ru.kuzmina.lesson3.SafeCounter;

public class TestCounter {
    public static void main(String[] args) throws InterruptedException {

        SafeCounter counter = new SafeCounter();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                counter.decrement();
            }
        });

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("counter = " + counter.getCount());
    }

}
