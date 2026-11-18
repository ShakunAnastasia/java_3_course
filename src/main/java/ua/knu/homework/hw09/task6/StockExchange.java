package ua.knu.homework.hw09.task6;

import java.util.Collections;
import java.util.List;

public class StockExchange {
    private final List<Stock> stocks;
    private final double initialIndex;
    private final double crashThresholdPercent;
    private volatile boolean tradingHalted = false;
    private int totalTransactions = 0;

    public StockExchange(List<Stock> stocks, double crashThresholdPercent) {
        this.stocks = Collections.unmodifiableList(stocks);
        this.crashThresholdPercent = crashThresholdPercent;
        this.initialIndex = calculateRawIndex();
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public boolean isTradingHalted() {
        return tradingHalted;
    }

    public double getInitialIndex() {
        return initialIndex;
    }

    public double getCurrentIndex() {
        return calculateRawIndex();
    }

    private double calculateRawIndex() {
        double sum = 0.0;
        for (Stock s : stocks) {
            sum += s.getPrice();
        }
        return stocks.isEmpty() ? 0.0 : sum / stocks.size();
    }

    public synchronized void offerToMarket(String brokerName, Stock stock, int amount) {
        if (tradingHalted) {
            return;
        }
        stock.offerShares(amount);
        totalTransactions++;
        System.out.printf("[Offer] %-10s offered %3d shares of %-4s | New price: %7.2f | Pool: %4d | Index: %7.2f%n",
                brokerName, amount, stock.getTicker(), stock.getPrice(), stock.getAvailableShares(), getCurrentIndex());
        checkMarketCrash();
    }

    public synchronized void executeBuy(String brokerName, Stock stock, int amount) {
        if (tradingHalted) {
            return;
        }
        int bought = stock.buyShares(amount);
        if (bought > 0) {
            totalTransactions++;
            System.out.printf("[Buy]   %-10s bought  %3d shares of %-4s | New price: %7.2f | Pool: %4d | Index: %7.2f%n",
                    brokerName, bought, stock.getTicker(), stock.getPrice(), stock.getAvailableShares(), getCurrentIndex());
            checkMarketCrash();
        }
    }

    public synchronized void executeSell(String brokerName, Stock stock, int amount) {
        if (tradingHalted) {
            return;
        }
        stock.sellShares(amount);
        totalTransactions++;
        System.out.printf("[Sell]  %-10s sold    %3d shares of %-4s | New price: %7.2f | Pool: %4d | Index: %7.2f%n",
                brokerName, amount, stock.getTicker(), stock.getPrice(), stock.getAvailableShares(), getCurrentIndex());
        checkMarketCrash();
    }

    private void checkMarketCrash() {
        double currentIndex = getCurrentIndex();
        double dropPercent = ((initialIndex - currentIndex) / initialIndex) * 100.0;
        if (!tradingHalted && dropPercent >= crashThresholdPercent) {
            tradingHalted = true;
            System.out.printf("%n!!! CIRCUIT BREAKER TRIGGERED: Exchange index dropped by %.2f%% (from %.2f to %.2f). Trading HALTED! !!!%n%n",
                    dropPercent, initialIndex, currentIndex);
        }
    }

    public int getTotalTransactions() {
        return totalTransactions;
    }
}