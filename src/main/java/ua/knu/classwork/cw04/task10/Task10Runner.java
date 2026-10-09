package ua.knu.classwork.cw04.task10;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Task10Runner {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner scanner = new Scanner(System.in);

        Bouquet bouquet = new Bouquet();
        bouquet.addFlower(new Rose(80.0, 3, 50.0));
        bouquet.addFlower(new Rose(95.0, 1, 65.0));
        bouquet.addFlower(new Tulip(45.0, 2, 35.0));
        bouquet.addFlower(new Chamomile(30.0, 4, 25.0));
        bouquet.addAccessory(new Accessory("Decorative Ribbon", 20.0));
        bouquet.addAccessory(new Accessory("Kraft Paper Wrap", 40.0));

        boolean running = true;
        while (running) {
            System.out.println("\n~~ Flower Shop Menu ~~");
            System.out.println("1. Show bouquet details and total cost");
            System.out.println("2. Sort flowers by freshness");
            System.out.println("3. Find flowers by stem length range");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Exiting.");
                break;
            }

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bouquet.printBouquet();
                    break;
                case 2:
                    bouquet.sortByFreshness();
                    System.out.println("Bouquet flowers sorted by freshness successfully.");
                    bouquet.printBouquet();
                    break;
                case 3:
                    System.out.print("Enter min stem length (cm): ");
                    double min = scanner.nextDouble();
                    System.out.print("Enter max stem length (cm): ");
                    double max = scanner.nextDouble();
                    List<Flower> found = bouquet.findByStemLength(min, max);
                    System.out.printf("Flowers with stem length in [%.1f, %.1f]:%n", min, max);
                    if (found.isEmpty()) {
                        System.out.println("No flowers matched the criteria.");
                    } else {
                        for (Flower f : found) {
                            System.out.println(" -> " + f);
                        }
                    }
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting Flower Shop.");
                    break;
                default:
                    System.out.println("Unknown option. Please try again.");
            }
        }

        scanner.close();
    }
}