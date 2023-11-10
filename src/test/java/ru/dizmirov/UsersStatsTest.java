package ru.dizmirov;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersStatsTest {

    @Test
    public void testOnUserCall() {
        var usersStats = new UsersStats();

        assertEquals(1, usersStats.onUserCall("user1"));
        assertEquals(2, usersStats.onUserCall("user1"));
        assertEquals(1, usersStats.onUserCall("user2"));
    }

    @Test
    public void testGetHitsOf() {
        var usersStats = new UsersStats();
        usersStats.onUserCall("user1");
        usersStats.onUserCall("user1");
        usersStats.onUserCall("user2");

        assertEquals(2, usersStats.getHitsOf("user1"));
        assertEquals(1, usersStats.getHitsOf("user2"));
        assertEquals(0, usersStats.getHitsOf("nonexistentUser"));
    }

    @Test
    public void testMultiThreadedUsage() throws InterruptedException {
        var usersStats = new UsersStats();
        int numThreads = 10;
        int numCallsPerThread = 100;

        var executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < numCallsPerThread; j++) {
                    usersStats.onUserCall("user1");
                    usersStats.onUserCall("user2");
                    usersStats.onUserCall("user3");
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);

        // Total hits expected: numThreads * numCallsPerThread
        assertEquals(numThreads * numCallsPerThread, usersStats.getHitsOf("user1"));
        assertEquals(numThreads * numCallsPerThread, usersStats.getHitsOf("user2"));
        assertEquals(numThreads * numCallsPerThread, usersStats.getHitsOf("user3"));
    }
}