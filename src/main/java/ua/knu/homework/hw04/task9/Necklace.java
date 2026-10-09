package ua.knu.homework.hw04.task9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Necklace {
    private final List<Gemstone> stones = new ArrayList<>();

    public void addStone(Gemstone stone) {
        stones.add(stone);
    }

    public double calculateTotalWeight() {
        return stones.stream().mapToDouble(Gemstone::getWeightCarats).sum();
    }

    public double calculateTotalPrice() {
        return stones.stream().mapToDouble(Gemstone::getTotalPrice).sum();
    }

    public void sortByValue() {
        stones.sort(Comparator.comparingDouble(Gemstone::getTotalPrice).reversed());
    }

    public List<Gemstone> findByTransparencyRange(double minTransp, double maxTransp) {
        List<Gemstone> result = new ArrayList<>();
        for (Gemstone s : stones) {
            if (s.getTransparencyPercent() >= minTransp && s.getTransparencyPercent() <= maxTransp) {
                result.add(s);
            }
        }
        return result;
    }

    public void printNecklace() {
        System.out.println("~ Necklace Stones ~");
        for (Gemstone s : stones) {
            System.out.println(" * " + s);
        }
        System.out.printf("Total Weight: %.2f carats | Total Price: %.2f USD%n",
                calculateTotalWeight(), calculateTotalPrice());
    }
}