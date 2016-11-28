package ru.sbt.javaschool;

import java.util.concurrent.Semaphore;

/*
* @author Tomskikh Maksim
*/
public class HairDresser implements Runnable {
    private Client client;

    /**
     * Semaphore for one hairdresser
     */
    private static Semaphore hairdresser = new Semaphore(1);

    public HairDresser(Client client) {
        this.client = client;
//        System.out.println("CUT CUT CUT");
    }

    /**
     * Cut client and lets him to go home
     */
    @Override
    public void run() {
        synchronized (client) {
            try {
                hairdresser.acquire();
                System.out.println("Hairdresser begin  cut " + client.getName());
                Thread.sleep(400);
                System.out.println("Hairdresser finished  " + client.getName());
                hairdresser.release();
                client.latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
