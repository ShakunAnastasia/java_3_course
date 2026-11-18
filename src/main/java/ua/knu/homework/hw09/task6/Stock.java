package ua.knu.homework.hw09.task6;

public class Stock {
    private final String ticker;
    private final String companyName;
    private double price;
    private int availableShares;

    public Stock(String ticker, String companyName, double initialPrice, int initialShares) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.price = initialPrice;
        this.availableShares = initialShares;
    }

    public String getTicker() {
        return ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public synchronized double getPrice() {
        return price;
    }

    public synchronized int getAvailableShares() {
        return availableShares;
    }

    public synchronized void offerShares(int amount) {
        if (amount > 0) {
            availableShares += amount;
            double dropFactor = 1.0 - Math.min(0.05, amount * 0.0005);
            price = Math.max(1.0, price * dropFactor);
        }
    }

    public synchronized int buyShares(int requestedAmount) {
        int bought = Math.min(requestedAmount, availableShares);
        if (bought > 0) {
            availableShares -= bought;
            double growthFactor = 1.0 + Math.min(0.08, bought * 0.0012);
            price *= growthFactor;
        }
        return bought;
    }

    public synchronized void sellShares(int amount) {
        if (amount > 0) {
            availableShares += amount;
            double dropFactor = 1.0 - Math.min(0.10, amount * 0.0015);
            price = Math.max(1.0, price * dropFactor);
        }
    }
}