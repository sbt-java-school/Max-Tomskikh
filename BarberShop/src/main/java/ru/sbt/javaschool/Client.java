package ru.sbt.javaschool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
* @author Tomskikh Maksim
*/
public class Client implements Runnable {
    private String name;

    /**
     * Semaphore to get to Salon
     */
    private Semaphore semaphoreSalon;
    /**
     * Latch to go home
     */
    CountDownLatch latch = new CountDownLatch(1);

    public Client(String name, Semaphore semaphoreSalon) {
        this.name = name;
        this.semaphoreSalon = semaphoreSalon;
    }

    public String getName() {
        return name;
    }

    /**
     * Client try to get to salon 400 milliseconds, if it's successfully go home
     *
     */
    @Override
    public void run() {
        try {

            if (semaphoreSalon.tryAcquire(400, TimeUnit.MILLISECONDS)) {
                System.out.println(name + " go into salon and wait");
                HairDresser hairdresser = new HairDresser(this);
                Thread thread = new Thread(hairdresser);
                thread.start();
                latch.await();
                semaphoreSalon.release();
                System.out.println(name + " Client has new HairStyle");
            } else {
                System.out.println(name + " Unsuccessfully");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
