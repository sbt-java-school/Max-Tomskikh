package ru.sbt.javaschool;

import java.util.Arrays;
import java.util.List;


public class TruckDaoImpl implements TruckDao {

    @Override
    public List<Truck> list() {
        return Arrays.asList(new Truck(3, 45),
                new Truck(4, 21),
                new Truck(6, 43),
                new Truck(1, 23));
    }
}
