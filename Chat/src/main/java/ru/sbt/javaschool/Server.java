package ru.sbt.javaschool;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

/*
* @author Tomskikh Maksim
*/
public class Server {
    private static final Logger LOGGER = Logger.getLogger(Server.class);
    private static final int MAX_COUNT_OF_CustomerS = 10;
    public static final int PORT = 1234;
    private static Map<Customer, ArrayList<Object>> messagesMap = new ConcurrentHashMap<>();
    private static final Customer SYSTEM = new Customer("SYSTEM");

    private Server() {
    }
    //Create Server Socket
    public static void main(String[] args) throws IOException {
        LOGGER.info("Chat started!");
        try (ServerSocket server = new ServerSocket(PORT)) {
            ExecutorService service = Executors.newFixedThreadPool(MAX_COUNT_OF_CustomerS);
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Socket customerSocket = server.accept();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(customerSocket.getInputStream()));

                    String login = reader.readLine();
                    Customer customer = new Customer(login, customerSocket, new PrintWriter(customerSocket.getOutputStream()), reader);

                    checkSameLogins(login, customer);

                    //Notify new Customer
                    notifyCustomers(customer.getLogin() + " has entered to school chat!");
                    //создаем для нового пользователя историю сообщений
                    messagesMap.put(customer, new ArrayList<>());
                    LOGGER.info(customer.getLogin() + " connected");

                    //Check commands of customer
                    service.submit(new Notification(customer));
                } catch (Exception e) {
                    LOGGER.error("Exception!"+ e);
                }
            }
        }
    }
    //Chek login of customer

    private static void checkSameLogins(String login, Customer Customer) {
        boolean anyMatch = messagesMap.entrySet().stream()
                .anyMatch(p -> p.getKey().getLogin().equals(Customer.getLogin()));
        if (anyMatch) {
            Pattern compile = Pattern.compile("^" + login + "\\[\\d+\\]$");
            long count = messagesMap.entrySet().stream()
                    .filter(p -> compile.matcher(p.getKey().getLogin()).matches()).count();

            Customer.setLogin(Customer.getLogin() + "[" + (count + 1) + "]");
            sendToUser(SYSTEM, Customer,
                    "Your login is same with another Customer in Chat. Your new login is "
                            + Customer.getLogin());
        }
    }

    //@param Message from customer
    private static void notifyCustomers(String message) {
        for (Customer Customer : messagesMap.keySet()) {
            sendToUser(SYSTEM, Customer, message);
        }
    }

    //@Message send to customer
    private static void sendToUser(Customer from, Customer to, String message) {
        to.getPrintWriter().println(from.getLogin() + " >> " + message);
        to.getPrintWriter().flush();
    }

    private static class Notification implements Runnable {
        private final BufferedReader reader;
        private final Customer Customer;

        public Notification(Customer Customer) {
            this.reader = Customer.getBufferedReader();
            this.Customer = Customer;
        }

        @Override
        public void run() {
            String s;
            try {
                while ((s = reader.readLine()) != null) {
                    switch (s) {
                        case "getmessages":
                            LOGGER.info(Customer.getLogin() + " required his messages");
                            String answerToCustomer = messagesMap.get(Customer).toString();
                            LOGGER.info("\t" + answerToCustomer);
                            sendToUser(SYSTEM, Customer, answerToCustomer);
                            break;
                        case "activeusers":
                            LOGGER.info(Customer.getLogin() + " required active users");
                            String answerToCustomer1 = messagesMap.keySet().toString();
                            LOGGER.info("\t" + answerToCustomer1);
                            sendToUser(SYSTEM, Customer, answerToCustomer1);
                            break;
                        case "disconnect":
                            disconnectUser();
                            break;
                        //отправляем сообщение
                        default:
                            prepareToSent(s);
                            break;
                    }
                }
            } catch (IOException e) {
                LOGGER.error(Customer.getLogin() + e);
            }
        }

        private void prepareToSent(String s) {
            String[] splitResult = s.split(">>", 2);
            LOGGER.info("From " + Customer.getLogin() + " to " + splitResult[0] + ":\n\t" + splitResult[1]);
            Optional<Customer> first = messagesMap.keySet().stream()
                    .filter(p -> p.getLogin().equals(splitResult[0]))
                    .findFirst();
            if (first.isPresent()) {
                messagesMap.get(first.get()).add(Customer.getLogin() + " >> " + splitResult[1]);
                sendToUser(Customer, first.get(), splitResult[1]);
            }
        }

        private void disconnectUser() throws IOException {
            messagesMap.remove(Customer);
            Customer.getSocket().close();
            String disconnectMessage = Customer.getLogin() + " has disconnected from SchoolChat";
            LOGGER.info(disconnectMessage);
            notifyCustomers(disconnectMessage);
        }
    }
}
