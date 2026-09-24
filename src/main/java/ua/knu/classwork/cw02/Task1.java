package ua.knu.classwork.cw02;

public class Task1 {
    public static void main(String[] args) {

        int a = 0x7FFFFFFF; // 01111111_11111111_11111111_11111111
        int b = 0x80000001; // 10000000_00000000_00000000_00000001

        System.out.println("Initial values:");
        printBinary("a", a);
        printBinary("b", b);

        System.out.println("\nBitwise operations:");
        printBinary("~a ", ~a);
        printBinary("~b ", ~b);
        printBinary("a & b ", a & b);
        printBinary("a | b ", a | b);
        printBinary("a ^ b ", a ^ b);

        System.out.println("\nShift operations:");
        printBinary("a << 2  ", a << 2);
        printBinary("b << 2  ", b << 2);
        printBinary("a >> 2  ", a >> 2);
        printBinary("b >> 2  ", b >> 2);
        printBinary("a >>> 2 ", a >>> 2);
        printBinary("b >>> 2 ", b >>> 2);
    }

    private static void printBinary(String label, int value) {
        // Formats to 32 bits with leading zeros for clear representation
        String binary = String.format("%32s", Integer.toBinaryString(value)).replace(' ', '0');
        System.out.printf("%-32s : %s (dec: %d)%n", label, binary, value);
    }
}
