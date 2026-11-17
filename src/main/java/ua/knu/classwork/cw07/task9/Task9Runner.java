package ua.knu.classwork.cw07.task9;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Task9Runner {
    public static void main(String[] args) {
        int smallN = 10;
        System.out.println("~ Verification on Small Circle (N = " + smallN + ") ~");
        System.out.println("ArrayList survivor:  #" + solveWithArrayList(smallN));
        System.out.println("LinkedList survivor: #" + solveWithLinkedList(smallN));

        int largeN = 100_000;
        System.out.println("\n~ Performance Benchmark (N = " + largeN + ") ~");

        long startArrayList = System.nanoTime();
        int survivorArray = solveWithArrayList(largeN);
        long timeArrayListMs = (System.nanoTime() - startArrayList) / 1_000_000;

        long startLinkedList = System.nanoTime();
        int survivorLinked = solveWithLinkedList(largeN);
        long timeLinkedListMs = (System.nanoTime() - startLinkedList) / 1_000_000;

        System.out.printf("ArrayList  -> Survivor: #%d | Execution Time: %d ms%n", survivorArray, timeArrayListMs);
        System.out.printf("LinkedList -> Survivor: #%d | Execution Time: %d ms%n", survivorLinked, timeLinkedListMs);

        System.out.println("\n~ Conclusion ~");
        if (timeLinkedListMs < timeArrayListMs) {
            System.out.println("LinkedList is faster because Iterator.remove() runs in O(1) time without shifting elements, whereas ArrayList.remove() shifts trailing elements in O(N) time.");
        } else {
            System.out.println("ArrayList finished faster on small/cache-friendly sizes, though LinkedList scales better asymptotically for O(1) iterator removals.");
        }
    }

    public static int solveWithArrayList(int n) {
        List<Integer> circle = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }

        int index = 0;
        while (circle.size() > 1) {
            index = (index + 1) % circle.size();
            circle.remove(index);
        }
        return circle.get(0);
    }

    public static int solveWithLinkedList(int n) {
        List<Integer> circle = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            circle.add(i);
        }

        boolean skipNext = true;
        while (circle.size() > 1) {
            Iterator<Integer> iterator = circle.iterator();
            while (iterator.hasNext() && circle.size() > 1) {
                iterator.next();
                if (skipNext) {
                    skipNext = false;
                } else {
                    iterator.remove();
                    skipNext = true;
                }
            }
        }
        return circle.get(0);
    }
}