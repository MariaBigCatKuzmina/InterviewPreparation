package ru.kuzmina.lesson3.PingPong;

public class TestPingPong {
    public static void main(String[] args) {
            PingPong pingPong = new PingPong(5);

            Thread pingThread = new Thread(pingPong::ping);
            Thread pongThread = new Thread(pingPong::pong);

            pingThread.start();
            pongThread.start();
    }
}
