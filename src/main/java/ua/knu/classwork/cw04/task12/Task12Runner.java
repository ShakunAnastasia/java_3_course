package ua.knu.classwork.cw04.task12;

public class Task12Runner {
    public static void main(String[] args) {
        Payment order = new Payment();

        order.addItem("Mechanical Keyboard", 2800.0, 1);
        order.addItem("USB-C Cable", 350.0, 2);
        order.addItem("Mousepad", 450.0, 1);

        order.printReceipt();
    }
}
