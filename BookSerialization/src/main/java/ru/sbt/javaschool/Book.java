package ru.sbt.javaschool;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class Book implements Serializable {
    private static final long versionID = 1231212324L;
    private String title;
    private String author;
    private String genre;
    private int yearof;

    public Book(String title, String author, String genre, int yearof) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.yearof = yearof;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYearof(int yearof) {
        this.yearof = yearof;
    }

    public static long getVersionID() {
        return versionID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getYearof() {
        return yearof;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", yearof=" + yearof +
                '}';
    }



    private static class BookProxy implements Serializable {
        private static final long versionId = 1231231231L;
        private String allInfo;

        public BookProxy(Book ourBook){
            allInfo = ourBook.getAuthor() + "," + ourBook.getTitle() + "," + ourBook.getGenre() + "," + ourBook.getYearof();
        }
        private Object readResolve () throws ObjectStreamException {
            String [] pieces = allInfo.split(",");
            Book result = new Book(pieces[0],pieces[1],pieces[2],Integer.parseInt(pieces[3]));
            return result;
        }
    }
}