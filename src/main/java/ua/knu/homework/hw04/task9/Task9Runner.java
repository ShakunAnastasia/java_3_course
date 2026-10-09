package ua.knu.homework.hw04.task9;

import java.util.List;

public class Task9Runner {
    public static void main(String[] args) {
        Necklace necklace = new Necklace();

        necklace.addStone(new PreciousStone("Diamond", 1.5, 4000.0, 95.0));
        necklace.addStone(new PreciousStone("Emerald", 2.0, 2500.0, 85.0));
        necklace.addStone(new PreciousStone("Ruby", 1.8, 3000.0, 88.0));
        necklace.addStone(new SemipreciousStone("Amethyst", 4.0, 80.0, 75.0));
        necklace.addStone(new SemipreciousStone("Topaz", 3.5, 120.0, 80.0));
        necklace.addStone(new SemipreciousStone("Garnet", 5.0, 50.0, 60.0));

        System.out.println("~~ Initial Necklace ~~");
        necklace.printNecklace();

        System.out.println("\n~~ Sorted By Value (Total Price Descending) ~~");
        necklace.sortByValue();
        necklace.printNecklace();

        System.out.println("\n~~ Stones with Transparency Between 70.0% and 85.0% ~~");
        List<Gemstone> filtered = necklace.findByTransparencyRange(70.0, 85.0);
        for (Gemstone g : filtered) {
            System.out.println(" -> " + g);
        }
    }
}