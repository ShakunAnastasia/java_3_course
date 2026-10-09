package ua.knu.classwork.cw04.task6.pkg_b;

import ua.knu.classwork.cw04.task6.pkg_a.Parent;

public class Task6Runner {
    public static void main(String[] args) {
        System.out.println("Demonstration of Access Modifiers: ");

        // Спроба прямого виклику parent.protectedMethod() тут не скомпілюється:
        // Parent parent = new Parent();
        // parent.protectedMethod();

        System.out.println("1. Direct call parent.protectedMethod() outside pkg_a is BLOCKED by compiler.");

        System.out.println("\n2. Access via subclass outside package:");
        Child child = new Child();
        child.testInheritedAccess();
    }
}
