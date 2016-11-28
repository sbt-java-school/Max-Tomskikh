package ru.sbt.javaschool;


public class Main {

    /**
     * Realization cache proxy
     * @author Tomskikh Maksim
     * @param args The array of string arguments
     */
    public static void main(String[] args) {
        CalculatorImpl calculator = new Calculator();
        CalculatorImpl proxyCalculator = (CalculatorImpl) ProxyUtils.makeCached(calculator);
        System.out.println(proxyCalculator.getFact(32));
        System.out.println(proxyCalculator.getFact(5));
        System.out.println(proxyCalculator.squareOfTwo(5));
    }
}
