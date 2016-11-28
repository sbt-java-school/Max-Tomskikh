package ru.sbt.javaschool;


import org.junit.*;

import java.io.*;

/**
 * Test class for Book.class
 * @author Tomskikh Maksim
 */

public class BookTest {

    private String fileName = "book.ser";
    private Book bookAfterSer = null;
    private Book bookBeforeSer = new Book("The War of the Worlds", "Herbert George Wells", "novel", 1897);

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Test Book.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(bookBeforeSer);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
            bookAfterSer = (Book) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProxySerializationObject() {
        Assert.assertNotSame(bookAfterSer, bookBeforeSer);
    }

    @Test
    public void testProxySerializationStructure() {
        Assert.assertEquals(bookAfterSer.getTitle(), bookBeforeSer.getTitle());
        Assert.assertEquals(bookAfterSer.getAuthor(), bookBeforeSer.getAuthor());
        Assert.assertEquals(bookAfterSer.getGenre(), bookBeforeSer.getGenre());
        Assert.assertEquals(bookAfterSer.getYearof(),bookAfterSer.getYearof());
    }

    @After
    public void tearDown() {
        System.out.println("After");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Testing completed");
    }
}