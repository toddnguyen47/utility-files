package com.github.toddnguyen47.mycompletionservice;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import org.junit.Test;

public class MyCompletionServiceTest {

    @Test
    public void testBlah() throws IOException {
        Random random = new Random(Instant.now().toEpochMilli());
        List<Callable<Integer>> callables = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            final int jobNumber = i;
            callables.add(
                    () -> {
                        printJob(random, jobNumber);
                        return 0;
                    });
        }
        MyCompletionService.executeWithCompletionService(4, callables);
        System.out.println("Finished!");
    }

    private void printJob(Random random, int jobNumber) throws InterruptedException {
        int randomSleep = random.nextInt(1000);
        Thread.sleep(randomSleep);
        System.out.println("job " + jobNumber + ", slept for: " + randomSleep);
    }
}