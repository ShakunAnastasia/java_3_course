package ua.knu.classwork.cw09.task8;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class CashDesk implements Runnable {
    private final int deskId;
    private final LinkedBlockingDeque<Customer> queue = new LinkedBlockingDeque<>();
    private volatile boolean open = true;

    public CashDesk(int deskId) {
        this.deskId = deskId;
    }

    public int getDeskId() {
        return deskId;
    }

    public int getQueueSize() {
        return queue.size();
    }

    public void enqueue(Customer customer) {
        queue.offerLast(customer);
    }

    public boolean removeCustomer(Customer customer) {
        return queue.remove(customer);
    }

    public Customer peekFirst() {
        return queue.peekFirst();
    }

    public void close() {
        open = false;
    }

    @Override
    public void run() {
        try {
            while (open || !queue.isEmpty()) {
                Customer customer = queue.pollFirst(100, TimeUnit.MILLISECONDS);
                if (customer != null) {
                    customer.setBeingServed(true);
                    System.out.printf("[CashDesk #%d] Serving %s (time: %d ms | remaining in queue: %d)%n",
                            deskId, customer, customer.getServiceTimeMs(), queue.size());
                    Thread.sleep(customer.getServiceTimeMs());
                    System.out.printf("   [CashDesk #%d] Finished serving %s%n", deskId, customer);
                    customer.markServed();
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}