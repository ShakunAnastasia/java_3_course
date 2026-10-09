package ua.knu.classwork.cw04.task6.pkg_b;

import ua.knu.classwork.cw04.task6.pkg_a.Parent;

public class Child extends Parent {
    public void testInheritedAccess() {
        System.out.println("Child: calling protectedMethod via inheritance:");
        protectedMethod();
    }
}