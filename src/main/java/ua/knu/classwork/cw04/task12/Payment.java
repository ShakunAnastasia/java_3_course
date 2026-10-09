package ua.knu.classwork.cw04.task12;

import java.util.ArrayList;
import java.util.List;

public class Payment {
    private final List<Item> items = new ArrayList<>();

    public class Item {
        private final String name;
        private final double price;
        private final int quantity;

        public Item(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public double getSubtotal() {
            return price * quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return String.format("%s (Price: %.2f UAH, Qty: %d, Subtotal: %.2f UAH)",
                    name, price, quantity, getSubtotal());
        }
    }

    public void addItem(String name, double price, int quantity) {
        items.add(new Item(name, price, quantity));
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Item item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void printReceipt() {
        System.out.println("~~ Payment Receipt ~~");
        for (Item item : items) {
            System.out.println(" - " + item);
        }
        System.out.printf("Total Amount Payable: %.2f UAH%n", calculateTotal());
    }
}