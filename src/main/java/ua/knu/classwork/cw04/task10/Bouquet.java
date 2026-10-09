package ua.knu.classwork.cw04.task10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Bouquet {
    private final List<Flower> flowers = new ArrayList<>();
    private final List<Accessory> accessories = new ArrayList<>();

    public void addFlower(Flower flower) {
        flowers.add(flower);
    }

    public void addAccessory(Accessory accessory) {
        accessories.add(accessory);
    }

    public double calculateTotalCost() {
        double total = 0.0;
        for (Flower flower : flowers) {
            total += flower.getPrice();
        }
        for (Accessory accessory : accessories) {
            total += accessory.getPrice();
        }
        return total;
    }

    public void sortByFreshness() {
        flowers.sort(Comparator.comparingInt(Flower::getFreshnessLevel));
    }

    public List<Flower> findByStemLength(double minLength, double maxLength) {
        List<Flower> result = new ArrayList<>();
        for (Flower flower : flowers) {
            if (flower.getStemLengthCm() >= minLength && flower.getStemLengthCm() <= maxLength) {
                result.add(flower);
            }
        }
        return result;
    }

    public void printBouquet() {
        System.out.println("~ Bouquet Composition ~");
        for (Flower flower : flowers) {
            System.out.println(" * " + flower);
        }
        for (Accessory accessory : accessories) {
            System.out.println(" + Accessory: " + accessory);
        }
        System.out.printf("Total Cost: %.2f UAH%n", calculateTotalCost());
    }
}