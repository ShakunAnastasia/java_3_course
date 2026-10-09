package ua.knu.homework.hw04.task9;

import java.util.Objects;

public abstract class Gemstone {
    private String name;
    private double weightCarats;
    private double pricePerCarat;
    private double transparencyPercent;

    public Gemstone() {
        this("Unknown", 1.0, 100.0, 50.0);
    }

    public Gemstone(String name, double weightCarats, double pricePerCarat, double transparencyPercent) {
        this.name = name;
        this.weightCarats = weightCarats;
        this.pricePerCarat = pricePerCarat;
        this.transparencyPercent = transparencyPercent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeightCarats() {
        return weightCarats;
    }

    public void setWeightCarats(double weightCarats) {
        this.weightCarats = weightCarats;
    }

    public double getPricePerCarat() {
        return pricePerCarat;
    }

    public void setPricePerCarat(double pricePerCarat) {
        this.pricePerCarat = pricePerCarat;
    }

    public double getTransparencyPercent() {
        return transparencyPercent;
    }

    public void setTransparencyPercent(double transparencyPercent) {
        this.transparencyPercent = transparencyPercent;
    }

    public double getTotalPrice() {
        return weightCarats * pricePerCarat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gemstone gemstone = (Gemstone) o;
        return Double.compare(gemstone.weightCarats, weightCarats) == 0 &&
                Double.compare(gemstone.pricePerCarat, pricePerCarat) == 0 &&
                Double.compare(gemstone.transparencyPercent, transparencyPercent) == 0 &&
                Objects.equals(name, gemstone.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weightCarats, pricePerCarat, transparencyPercent);
    }

    @Override
    public String toString() {
        return String.format("%s [Weight: %.2f ct, Price/ct: %.2f USD, Total: %.2f USD, Transparency: %.1f%%]",
                name, weightCarats, pricePerCarat, getTotalPrice(), transparencyPercent);
    }
}