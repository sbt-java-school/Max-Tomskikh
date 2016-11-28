package ru.sbt.javaschool;


public class PutWeightInTruck {
    int currentWeight =0;
    int count;
    public void put(int [] w,int max){
        for (int i = 0; i <w.length ; i++) {
            if (currentWeight + w[i] < max) {
                count++;
                currentWeight+= w[i];
            }
        }
        System.out.println("Вместимость " + currentWeight);
        System.out.println("Количество грузов " + count);
    }
}

