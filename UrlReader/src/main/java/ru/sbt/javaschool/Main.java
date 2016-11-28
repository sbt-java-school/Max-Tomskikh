package ru.sbt.javaschool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/*
@author Tomskikh Maksim
@param ввод ссылки на html страницу в формате <http://.....>
*/
public class Main {
    public static void main(String[] args) throws UrlException, IOException {
        try {
            InputStream in = System.in;
            InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
            BufferedReader reader = new BufferedReader(inputStreamReader);
            {
                while (true) {
                    try {

                        System.out.print("Enter the URL:");
                        String adress = reader.readLine();
                        readContent(adress);
                        break;
                    } catch (UrlException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Broken");
        } catch (Exception e) {
            System.out.println("Unhandled exception");
        }
    }

    private static boolean readContent(String adress) throws UrlException {
        try {
            URL oururl = new URL(adress);
            try (InputStream in = oururl.openStream();
                InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");
                BufferedReader reader = new BufferedReader(inputStreamReader)){

                    while (true) {
                        String content = reader.readLine();
                        if (content != null) {
                            System.out.println(content);
                        } else {
                            return true;
                        }
                    }
                }
            } catch (MalformedURLException e) {
                throw new UrlException("Incorrect URL");
            } catch (IOException e) {
                throw new UrlException("Check the network");
            }
        }
    }
