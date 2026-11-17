package ua.knu.homework.hw08.task5;

public class Airplane implements Runnable {
    private final String flightNumber;
    private final int capacity;
    private final int maxRangeZone;
    private final String destination;
    private final Airport airport;

    public Airplane(String flightNumber, int capacity, int maxRangeZone, String destination, Airport airport) {
        this.flightNumber = flightNumber;
        this.capacity = capacity;
        this.maxRangeZone = maxRangeZone;
        this.destination = destination;
        this.airport = airport;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getMaxRangeZone() {
        return maxRangeZone;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public void run() {
        try {
            airport.processFlight(this);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Flight " + flightNumber;
    }
}