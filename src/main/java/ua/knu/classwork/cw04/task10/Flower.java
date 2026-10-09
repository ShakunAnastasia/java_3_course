package ua.knu.classwork.cw04.task10;

public abstract class Flower {
    private final String name;
    private final double price;
    private final int freshnessLevel;
    private final double stemLengthCm;

    public Flower(String name, double price, int freshnessLevel, double stemLengthCm) {
        this.name = name;
        this.price = price;
        this.freshnessLevel = freshnessLevel;
        this.stemLengthCm = stemLengthCm;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getFreshnessLevel() {
        return freshnessLevel;
    }

    public double getStemLengthCm() {
        return stemLengthCm;
    }

    @Override
    public String toString() {
        return String.format("%s (Price: %.2f UAH, Freshness: %d, Stem: %.1f cm)",
                name, price, freshnessLevel, stemLengthCm);
    }
}