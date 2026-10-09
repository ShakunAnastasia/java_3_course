package ua.knu.classwork.cw04.task10;

public class Accessory {
    private final String name;
    private final double price;

    public Accessory(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s (%.2f UAH)", name, price);
    }
}