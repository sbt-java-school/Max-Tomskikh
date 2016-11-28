package ru.sbt.javaschool;
/**
 * Class simulates the task,witch create random number [0,7]
 *
 *  @author Tomskikh Maksim
 */

public class ThreadWork implements Runnable {
    private int number;

    public ThreadWork() {
        number = (int) (Math.random() * 8); //  [0;7]
    }


    @Override
    public void run() {
        System.out.println("Start create random number  :" + number);
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
             e.printStackTrace();
        }
    }
}
