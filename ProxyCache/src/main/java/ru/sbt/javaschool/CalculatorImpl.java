package ru.sbt.javaschool;

import java.math.BigInteger;

public interface CalculatorImpl {
    @Cache
    double squareOfTwo(double a);

    @Cache
    BigInteger getFact(int i);
}
