package ru.sbt.javaschool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
@author Tomskikh Maksim
Realization ThreadPool with library Executor
*/
    public class SimpleThreadPoll {

        public static void main(String[] args) {
            ExecutorService executor = Executors.newFixedThreadPool(4);
            for (int i = 0; i < 11; i++) {
                Runnable workerThread = new DoThread();
                executor.execute(workerThread);
            }
            executor.shutdown();
            while (!executor.isTerminated()) {
            }
            System.out.println("Finish");
        }
    }
