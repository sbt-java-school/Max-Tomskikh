package ru.sbt.javaschool;

import org.junit.*;

/**
 * Created by Max on 26.11.2016.
 */
public class ThreadPoolTest {
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Begin testing ThreadPool.class");
    }

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @Test
    public void testThreadPool() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(1, 6);
        for (int i = 0; i < 10; i++) {
            ThreadWork task = new ThreadWork();
            threadPool.submitTask(task);
        }
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptyThreadPool() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(0, 4);
        for (int i = 0; i < 10; i++) {
            ThreadWork threadWork = new ThreadWork();
            threadPool.submitTask(threadWork);
        }
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

