package ua.knu.homework.hw08.task5;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Airport {
    private final Semaphore terminals;
    private final Semaphore airstairs;
    private final Map<String, Integer> destinationRanges;

    public Airport(int terminalCount, int airstairCount) {
        this.terminals = new Semaphore(terminalCount, true);
        this.airstairs = new Semaphore(airstairCount, true);

        Map<String, Integer> routes = new LinkedHashMap<>();
        routes.put("Lviv", 1);
        routes.put("Warsaw", 1);
        routes.put("Frankfurt", 2);
        routes.put("London", 2);
        routes.put("New York", 3);
        this.destinationRanges = Collections.unmodifiableMap(routes);
    }

    public int getRangeZone(String destination) {
        return destinationRanges.getOrDefault(destination, 1);
    }

    public Map<String, Integer> getDestinationRanges() {
        return destinationRanges;
    }

    public void processFlight(Airplane plane) throws InterruptedException {
        int requiredZone = getRangeZone(plane.getDestination());
        if (plane.getMaxRangeZone() < requiredZone) {
            System.out.printf("[REJECTED] %s cannot fly to %s (Zone %d required, plane max Zone %d)%n",
                    plane, plane.getDestination(), requiredZone, plane.getMaxRangeZone());
            return;
        }

        boolean useTerminal = plane.getCapacity() >= 180;
        Semaphore resource = useTerminal ? terminals : airstairs;
        String resourceName = useTerminal ? "Terminal" : "Airstair";

        System.out.printf("[Waiting] %s -> %s (Zone %d, cap: %d) waiting for %s...%n",
                plane, plane.getDestination(), requiredZone, plane.getCapacity(), resourceName);

        resource.acquire();
        try {
            int serviceTimeMs = 400 + plane.getCapacity() * 3;
            System.out.printf("   [Docked] %s docked at %s (free %ss: %d). Boarding/disembarking (%d ms)...%n",
                    plane, resourceName, resourceName, resource.availablePermits(), serviceTimeMs);
            Thread.sleep(serviceTimeMs);
            System.out.printf("   [Departed] %s finished at %s and departed to %s!%n",
                    plane, resourceName, plane.getDestination());
        } finally {
            resource.release();
        }
    }
}