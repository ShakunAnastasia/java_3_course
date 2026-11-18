package ua.knu.classwork.cw09.task8;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class Customer {
    private final int id;
    private final int serviceTimeMs;
    private final CountDownLatch servedLatch = new CountDownLatch(1);
    private volatile boolean beingServed = false;

    public Customer(int id) {
        this.id = id;
        this.serviceTimeMs = ThreadLocalRandom.current().nextInt(400, 801);
    }

    public int getId() {
        return id;
    }

    public int getServiceTimeMs() {
        return serviceTimeMs;
    }

    public boolean isBeingServed() {
        return beingServed;
    }

    public void setBeingServed(boolean beingServed) {
        this.beingServed = beingServed;
    }

    public void markServed() {
        servedLatch.countDown();
    }

    public void awaitService() throws InterruptedException {
        servedLatch.await();
    }

    @Override
    public String toString() {
        return "Customer #" + id;
    }
}