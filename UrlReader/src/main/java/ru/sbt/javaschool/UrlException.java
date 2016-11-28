package ru.sbt.javaschool;

/**
 * Created by TMW on 1.09.2016.
 */
public class UrlException extends Exception {
    public UrlException(String s, Throwable e) {
        super(s, e);
    }

    public UrlException(String s) {
        super(s);
    }

    public UrlException() {
        super();
    }
}
