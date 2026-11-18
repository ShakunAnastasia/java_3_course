package ua.knu.homework.hw09.task6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Broker implements Runnable {
    private final String name;
    private final StockExchange exchange;
    private final Map<String, Integer> portfolio = new HashMap<>();
    private final int maxActions;

    public Broker(String name, StockExchange exchange, Map<String, Integer> initialPortfolio, int maxActions) {
        this.name = name;
        this.exchange = exchange;
        this.portfolio.putAll(initialPortfolio);
        this.maxActions = maxActions;
    }

    @Override
    public void run() {
        List<Stock> stocks = exchange.getStocks();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int step = 1; step <= maxActions; step++) {
            if (exchange.isTradingHalted()) {
                break;
            }

            Stock target = stocks.get(random.nextInt(stocks.size()));
            String ticker = target.getTicker();
            int owned = portfolio.getOrDefault(ticker, 0);
            int action = random.nextInt(100);

            if (step == 1 && owned >= 20) {
                int offerAmount = Math.max(5, owned / 3);
                portfolio.put(ticker, owned - offerAmount);
                exchange.offerToMarket(name, target, offerAmount);
            } else if (action < 40) {
                int buyAmount = random.nextInt(5, 25);
                int beforePool = target.getAvailableShares();
                exchange.executeBuy(name, target, buyAmount);
                int actualBought = Math.max(0, beforePool - target.getAvailableShares());
                portfolio.put(ticker, owned + actualBought);
            } else if (owned > 0) {
                int sellAmount = Math.min(owned, random.nextInt(10, 45));
                portfolio.put(ticker, owned - sellAmount);
                exchange.executeSell(name, target, sellAmount);
            }

            try {
                Thread.sleep(random.nextInt(80, 180));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}