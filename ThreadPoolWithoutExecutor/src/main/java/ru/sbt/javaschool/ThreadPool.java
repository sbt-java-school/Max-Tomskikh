package ru.sbt.javaschool;

/**
 * Class create number of WorkExecutor instances
 * @author Tomskikh Maksim
 */
public class ThreadPool {
    BlockingQueue queue;

    public ThreadPool(int queueSize, int nThread) {
        if (queueSize <= 0 || nThread <= 0) {
            throw new IllegalArgumentException();
        }
        queue = new BlockingQueue(queueSize);
        String threadName = null;
        WorkExecutor task = null;
        for (int count = 0; count < nThread; count++) {
            threadName = "Thread-" + count;
            task = new WorkExecutor(queue);
            Thread thread = new Thread(task, threadName);
            thread.start();
        }
    }

    /**
     * Method submit task to thread Pool
     * @param task
     */
    public void submitTask(Runnable task) throws InterruptedException {
        queue.enqueue(task);
    }

}
