package ru.sbt.javaschool;
/*
@author Tomskikh Maksim
HomeWork1(Truck)
*/
import java.io.IOException;

public class Truck {
    public static void main(String[] args) throws IOException {
        int[] truckWeights = {1, 5, 10, 8, 1, 11};
        int maxCapacity = 12;
        PutWeightInTruck truck = new PutWeightInTruck();
        truck.put(truckWeights,maxCapacity);
    }


}
