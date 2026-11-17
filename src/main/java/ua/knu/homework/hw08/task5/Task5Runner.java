package ua.knu.homework.hw08.task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task5Runner {
    public static void main(String[] args) throws InterruptedException {
        Airport airport = new Airport(2, 3);

        System.out.println("~ Airport Simulation Started (2 Terminals, 3 Airstairs) ~");
        System.out.println("Destinations & Range Zones: " + airport.getDestinationRanges());
        System.out.println("Rule: Capacity >= 180 uses Terminal; Capacity < 180 uses Airstair.\n");

        List<Airplane> flights = Arrays.asList(
                new Airplane("PS-101", 220, 3, "New York", airport),
                new Airplane("PS-102", 120, 1, "Lviv", airport),
                new Airplane("LH-204", 190, 2, "Frankfurt", airport),
                new Airplane("LO-305", 90, 1, "Warsaw", airport),
                new Airplane("BA-408", 250, 3, "London", airport),
                new Airplane("PS-510", 140, 1, "London", airport),
                new Airplane("FR-612", 150, 2, "Warsaw", airport),
                new Airplane("DL-715", 300, 3, "New York", airport)
        );

        List<Thread> threads = new ArrayList<>();
        for (Airplane flight : flights) {
            Thread t = new Thread(flight, flight.getFlightNumber());
            threads.add(t);
            t.start();
            Thread.sleep(120);
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\n~ All airport flight operations completed ~");
    }
}