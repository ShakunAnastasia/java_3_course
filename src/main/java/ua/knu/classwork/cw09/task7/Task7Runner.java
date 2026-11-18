package ua.knu.classwork.cw09.task7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Task7Runner {
    public static void main(String[] args) throws InterruptedException {
        int operatorsCount = 2;
        int clientsCount = 6;

        int t1ArriveMin = 100, t2ArriveMax = 300;
        int t3ServiceMin = 500, t4ServiceMax = 900;
        int t5RetryMin = 250, t6RetryMax = 500;

        Semaphore operators = new Semaphore(operatorsCount, true);
        List<Thread> clientThreads = new ArrayList<>();

        System.out.println("~ Call Center Simulation Started (" + operatorsCount + " operators, " + clientsCount + " clients) ~");

        for (int i = 1; i <= clientsCount; i++) {
            int clientId = i;
            Thread clientThread = new Thread(() -> {
                int attempts = 0;
                while (true) {
                    attempts++;
                    if (operators.tryAcquire()) {
                        try {
                            int serviceTime = ThreadLocalRandom.current().nextInt(t3ServiceMin, t4ServiceMax + 1);
                            System.out.printf("[Client #%d] Connected to operator (attempt #%d). Service time: %d ms | Free operators left: %d%n",
                                    clientId, attempts, serviceTime, operators.availablePermits());
                            Thread.sleep(serviceTime);
                            System.out.printf("   [Client #%d] Call finished. Operator freed.%n", clientId);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {
                            operators.release();
                        }
                        break;
                    } else {
                        int retryDelay = ThreadLocalRandom.current().nextInt(t5RetryMin, t6RetryMax + 1);
                        System.out.printf("   [Client #%d] All operators busy (attempt #%d). Hanging up, retrying in %d ms...%n",
                                clientId, attempts, retryDelay);
                        try {
                            Thread.sleep(retryDelay);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
            }, "Client-" + clientId);

            clientThreads.add(clientThread);
            clientThread.start();

            int nextArrivalDelay = ThreadLocalRandom.current().nextInt(t1ArriveMin, t2ArriveMax + 1);
            Thread.sleep(nextArrivalDelay);
        }

        for (Thread t : clientThreads) {
            t.join();
        }

        System.out.println("\n~ All " + clientsCount + " clients have been served. Call center closed. ~");
    }
}