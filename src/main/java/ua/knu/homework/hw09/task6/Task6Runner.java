package ua.knu.homework.hw09.task6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Task6Runner {
    public static void main(String[] args) throws InterruptedException {
        List<Stock> companies = Arrays.asList(
                new Stock("AAPL", "Apple Inc.", 180.0, 200),
                new Stock("MSFT", "Microsoft Corp.", 320.0, 150),
                new Stock("TSLA", "Tesla Inc.", 240.0, 180),
                new Stock("NVDA", "NVIDIA Corp.", 460.0, 120)
        );

        StockExchange exchange = new StockExchange(companies, 10.0);

        System.out.printf("~ Stock Exchange Opened (Initial Index: %.2f, Crash Halt Threshold: -10.0%%) ~%n%n",
                exchange.getInitialIndex());

        List<Broker> brokers = Arrays.asList(
                new Broker("Broker-1", exchange, Map.of("AAPL", 90, "TSLA", 110, "NVDA", 60), 10),
                new Broker("Broker-2", exchange, Map.of("MSFT", 80, "TSLA", 120, "AAPL", 70), 10),
                new Broker("Broker-3", exchange, Map.of("NVDA", 100, "MSFT", 75, "TSLA", 95), 10)
        );

        List<Thread> brokerThreads = new ArrayList<>();
        for (int i = 0; i < brokers.size(); i++) {
            Thread t = new Thread(brokers.get(i), "Broker-Thread-" + (i + 1));
            brokerThreads.add(t);
            t.start();
        }

        for (Thread t : brokerThreads) {
            t.join();
        }

        System.out.println("\n~Trading Session Summary ~");
        System.out.println("Status: " + (exchange.isTradingHalted() ? "HALTED due to sharp index drop" : "Completed normally"));
        System.out.println("Total executed transactions: " + exchange.getTotalTransactions());
        System.out.printf("Initial Index: %.2f -> Final Index: %.2f%n",
                exchange.getInitialIndex(), exchange.getCurrentIndex());
        for (Stock s : companies) {
            System.out.printf(" - %-4s (%-15s): final price = %7.2f, pool shares = %d%n",
                    s.getTicker(), s.getCompanyName(), s.getPrice(), s.getAvailableShares());
        }
    }
}