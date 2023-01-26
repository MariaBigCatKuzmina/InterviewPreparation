package ru.kuzmina.lesson3.PingPong;

public class PingPong {
    private volatile String currentState = "ping";
    private final int iterationCount;

    public PingPong(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    synchronized public void ping() {
        try {
            for (int i = 0; i < iterationCount; i++) {
                while (!currentState.equals("ping")) {
                    wait();
                }
                System.out.println(currentState);
                currentState = "pong";
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public void pong() {
        try {
            for (int i = 0; i < iterationCount; i++) {
                while (!currentState.equals("pong")) {
                    wait();
                }
                System.out.println(currentState);
                currentState = "ping";
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
