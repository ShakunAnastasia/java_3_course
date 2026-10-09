package ua.knu.homework.hw04.task9;

public class PreciousStone extends Gemstone {
    public PreciousStone() {
        super("Diamond", 1.0, 3000.0, 95.0);
    }

    public PreciousStone(String name, double weightCarats, double pricePerCarat, double transparencyPercent) {
        super(name, weightCarats, pricePerCarat, transparencyPercent);
    }
}