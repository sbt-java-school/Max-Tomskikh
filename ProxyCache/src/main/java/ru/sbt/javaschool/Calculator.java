package ru.sbt.javaschool;

import java.math.BigInteger;

import static java.lang.Math.sqrt;


public class Calculator implements CalculatorImpl {
    //Вычисление квадрата числа
    public  double squareOfTwo (double a){
        return sqrt (a);
    }
    //Вычисление факториала
    public BigInteger getFact(int i) {
        BigInteger bigI = BigInteger.ONE;
        if (i > 1) {
            for (int j = 2; j <= i; j++) {
                bigI = bigI.multiply(BigInteger.valueOf(j));
            }
        }
        return bigI;
    }
}
