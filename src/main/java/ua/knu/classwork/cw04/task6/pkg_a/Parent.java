package ua.knu.classwork.cw04.task6.pkg_a;

public class Parent {
    protected void protectedMethod() {
        System.out.println("Parent: protectedMethod() executed successfully.");
    }

    void packagePrivateMethod() {
        System.out.println("Parent: packagePrivateMethod() executed.");
    }

    public void callProtectedInsidePackage() {
        protectedMethod();
    }
}