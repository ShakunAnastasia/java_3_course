package ua.knu.classwork.cw08.task6;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Task6Runner {
    private static final String POISON_PILL = "STOP";

    public static void main(String[] args) throws InterruptedException {
        int totalMessages = 8;
        long t1Millis = 300;
        long t2Millis = 500;

        BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= totalMessages; i++) {
                    Thread.sleep(t1Millis);
                    String msg = "Message #" + i + " (created at " + System.currentTimeMillis() % 100000 + " ms)";
                    messageQueue.put(msg);
                    System.out.println("[Producer] Generated -> " + msg + " | Queue size: " + messageQueue.size());
                }
                messageQueue.put(POISON_PILL);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Producer-Thread");

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    String msg = messageQueue.take();
                    if (POISON_PILL.equals(msg)) {
                        System.out.println("[Consumer] Received stop signal. Shutting down.");
                        break;
                    }
                    System.out.println("   [Consumer] Processing -> " + msg);
                    Thread.sleep(t2Millis);
                    System.out.println("   [Consumer] Finished   -> " + msg);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Consumer-Thread");

        System.out.println("~ Starting Producer-Consumer Queue Simulation (t1 = " + t1Millis + "ms, t2 = " + t2Millis + "ms) ~");
        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
        System.out.println("\n~ All messages processed in FIFO order ~");
    }
}