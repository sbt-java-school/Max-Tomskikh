package ru.sbt.javaschool;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
* @author Tomskikh Maksim
*/

public class Barbershop {

    /**
     * Create barbershop with one hairdresser
     * @param salonSize count chairs into salon
     * @param clients count clients in a day
     */
    public void barbershopStart(int salonSize, int clients) {
        if (salonSize <= 0 || clients <= 0) {
            throw new IllegalArgumentException();
        }
        Semaphore semaphoreSalon = new Semaphore(salonSize);
        for (int i = 0; i < clients; i++) {
            Client client = new Client("Client " + i, semaphoreSalon);
            Thread thread = new Thread(client);
            thread.start();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
