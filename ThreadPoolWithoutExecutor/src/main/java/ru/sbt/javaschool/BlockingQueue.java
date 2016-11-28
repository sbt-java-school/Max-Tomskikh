package ru.sbt.javaschool;

import java.util.LinkedList;
import java.util.Queue;
/**
 * @author Tomskikh Maksim
 * Class simulates the blocking queue
 */

public class BlockingQueue {
    private Queue queue = new LinkedList();
    private int EMPTY = 0;
    private int MAX_TASK_IN_QUEUE = -1;

    public BlockingQueue(int size) {
        MAX_TASK_IN_QUEUE = size;
    }

    /**
     * Method  push the Task to the queue
     * @param task
     */
    public synchronized void enqueue(Runnable task) throws InterruptedException {
        while (queue.size() == MAX_TASK_IN_QUEUE) {
            wait();
        }
        if (queue.size() == EMPTY) {
            notifyAll();
        }
        queue.offer(task);
    }

    /**
     * Method to takes the task from the queue
     * @return task
     */
    public synchronized Runnable dequeue() throws InterruptedException {
        while (queue.size() == EMPTY) {
            wait();
        }
        if (queue.size() == MAX_TASK_IN_QUEUE) {
            notifyAll();
        }
        return (Runnable) queue.poll();
    }
}
