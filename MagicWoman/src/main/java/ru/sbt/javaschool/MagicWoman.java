package ru.sbt.javaschool;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* @author Tomskikh Maksim
*/
public class MagicWoman {
    // Initialization Logger
    private static final Logger LOGGER = Logger.getLogger(MagicWoman.class);
    public static final int MAX_MAGIC_NUMBER = 15;
    public static final int PORT = 1234;
    public static final int N_USERS = 10;
    private static final Random random = new Random();


    private MagicWoman() {
    }

    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(N_USERS);
        LOGGER.info("Start MagicWoman");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (!Thread.currentThread().isInterrupted()) {
                try{
                    Socket client = serverSocket.accept();
                    service.submit(new Guessing(client, random.nextInt(MAX_MAGIC_NUMBER)));
                }
                catch (Exception e){
                    LOGGER.error(e);
                }
            }
        }
    }
    //Guess the number
    private static class Guessing implements Runnable {

        private int magicNumber;
        private final Socket client;

        public Guessing(Socket client, int i) {
            this.client = client;
            this.magicNumber = i;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 PrintWriter writer = new PrintWriter(client.getOutputStream())) {
                writer.println("Guess the number");
                writer.flush();
                String line;
                while ((line = reader.readLine()) != null) {
                    int userNumber = Integer.parseInt(line);
                    checkUserInput(writer, userNumber);
                    writer.flush();
                }
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }

        private void checkUserInput(PrintWriter writer, int userNumber) {
            if (userNumber == magicNumber) {
                writer.println("Good work!!!");
                writer.println("Next Number!");
                magicNumber=random.nextInt(MAX_MAGIC_NUMBER);
            } else {
                writer.println("No! Try again");
            }
        }
    }
}
