package ru.sbt.javaschool;

public class Test extends ParentTest {
    public static final String JUNE = "JUNE";
    private static final double defaultValue = 13.0;
    double value;

    public Test() {
        this(defaultValue);
    }

    public Test(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}

class ParentTest extends GrandParentTest {
    private int x;

    private void doSomething() {
        System.out.println("doSomething");
    }
}

class GrandParentTest {
    static double calculate(double a, double b) {
        return a * b;
    }
}