package ru.sbt.javaschool;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/*
* @author Tomskikh Maksim
*/
public class Customer {
    private static final Logger LOGGER = Logger.getLogger(Customer.class);
    public static final int PORT = 1234;

    private Customer() {
    }

    public static void main(String[] args) throws IOException{
        try (Socket socket = new Socket(InetAddress.getLocalHost(), PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
            LOGGER.info("Welcome to our MagicPlace");
            new Thread(new Listener(reader)).start();
            readCommands(writer);
        }
    }

    private static void readCommands(PrintWriter writer) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = consoleReader.readLine()) != null) {
            writer.println(line);
            writer.flush();
        }
    }

    private static class Listener implements Runnable {

        private final BufferedReader reader;

        public Listener(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    LOGGER.info(line);
                }
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
    }
}

