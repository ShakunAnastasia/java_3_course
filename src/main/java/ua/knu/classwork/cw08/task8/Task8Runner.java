package ua.knu.classwork.cw08.task8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Task8Runner {
    public static void main(String[] args) throws InterruptedException {
        int desksCount = 3;
        int customersCount = 12;

        List<CashDesk> desks = new ArrayList<>();
        List<Thread> deskThreads = new ArrayList<>();

        for (int i = 1; i <= desksCount; i++) {
            CashDesk desk = new CashDesk(i);
            desks.add(desk);
            Thread t = new Thread(desk, "CashDesk-" + i);
            deskThreads.add(t);
            t.start();
        }

        System.out.println("~ Fast Food Restaurant Opened (" + desksCount + " cash desks, " + customersCount + " customers) ~");

        List<Thread> customerThreads = new ArrayList<>();

        for (int i = 1; i <= customersCount; i++) {
            Customer customer = new Customer(i);
            Thread customerWatcher = new Thread(() -> {
                CashDesk currentDesk = findShortestDesk(desks);
                currentDesk.enqueue(customer);
                System.out.printf("[Arrival] %s joined CashDesk #%d (queue size: %d)%n",
                        customer, currentDesk.getDeskId(), currentDesk.getQueueSize());

                try {
                    while (!customer.isBeingServed()) {
                        Thread.sleep(150);
                        if (customer.isBeingServed() || currentDesk.peekFirst() == customer) {
                            continue;
                        }

                        CashDesk bestDesk = findShortestDesk(desks);
                        if (bestDesk != currentDesk &&
                                bestDesk.getQueueSize() + 1 < currentDesk.getQueueSize()) {
                            if (currentDesk.removeCustomer(customer)) {
                                bestDesk.enqueue(customer);
                                System.out.printf("   [Switch] %s moved from CashDesk #%d to shorter CashDesk #%d (new size: %d)%n",
                                        customer, currentDesk.getDeskId(), bestDesk.getDeskId(), bestDesk.getQueueSize());
                                currentDesk = bestDesk;
                            }
                        }
                    }
                    customer.awaitService();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }, "Customer-" + i);

            customerThreads.add(customerWatcher);
            customerWatcher.start();

            Thread.sleep(ThreadLocalRandom.current().nextInt(80, 180));
        }

        for (Thread ct : customerThreads) {
            ct.join();
        }

        for (CashDesk desk : desks) {
            desk.close();
        }
        for (Thread dt : deskThreads) {
            dt.join();
        }

        System.out.println("\n~ All " + customersCount + " customers served. Restaurant closed. ~");
    }

    private static CashDesk findShortestDesk(List<CashDesk> desks) {
        return desks.stream()
                .min(Comparator.comparingInt(CashDesk::getQueueSize))
                .orElse(desks.get(0));
    }
}