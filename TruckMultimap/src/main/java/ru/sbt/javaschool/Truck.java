package ru.sbt.javaschool;



public class Truck {
    private long id;
    private int capacity;

    public Truck(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "id=" + id +
                ", capacity=" + capacity +
                '}';
    }
}
