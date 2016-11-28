package ru.sbt.javaschool;
/**
 * Class execute the task.
 * @author Tomskikh Maksim
 */

public class WorkExecutor implements Runnable {

    BlockingQueue queue;

    public WorkExecutor(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String name = Thread.currentThread().getName();
                Runnable task = queue.dequeue();
                System.out.println("Task Started by " + name);
                task.run();
                System.out.println("Task Finished by " + name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}