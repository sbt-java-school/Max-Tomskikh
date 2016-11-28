package ru.sbt.javaschool;

import java.util.concurrent.TimeUnit;

public class DoThread implements Runnable {
        private String command;

        public DoThread() {
            command = "Do Somthing";
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" Start " + command);
            doCommand();
            System.out.println(Thread.currentThread().getName()+" Stop");
        }
        private void doCommand() {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
