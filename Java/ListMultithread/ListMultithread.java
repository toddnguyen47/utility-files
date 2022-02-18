package multithreadtrial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListMultithread {

    private static final int NUM_THREADS = 4;
    private static final Logger LOGGER = LogManager.getLogger(ListMultithread.class);
    private static final ReentrantLock LOCK = new ReentrantLock();

    private int currentProgress = 0; // NOPMD
    private int listSize = 1;

    public static void main(final String[] args) {
        final ListMultithread lm = new ListMultithread();
        lm.execute();
    }

    public void execute() {
        listSize = 23;
        final List<String> newList = new ArrayList<>();
        final Random random = new Random();
        final int maxInt = 100;
        for (int i = 0; i < listSize; i++) {
            newList.add(String.valueOf(random.nextInt(maxInt + 1)));
        }
        try {
            runMultithread(newList);
        } catch (InterruptedException | ExecutionException e) {
            LOGGER.error("Exception occurred during multithread");
        }

        LOGGER.info("ALL DONE!");
    }

    public static String getProgressString(final int current, final int maxProgress) {
        // GUARD
        if (maxProgress <= 0) {
            return "MAX PROGRESS IS ZERO";
        }

        final double percentage = (current / (double) maxProgress) * 100.0;
        return String.format("PROCESSING: %d/%d, %.2f%%", current, maxProgress, percentage);
    }

    /**
     * <div> Run this service in multithreaded fashion. </div> <div> Ref:
     * https://stackoverflow.com/a/30655678/6323360 </div>
     *
     * @param inputList
     * @param client
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private void runMultithread(final List<?> inputList) throws InterruptedException, ExecutionException {

        final int listSize = inputList.size();
        final ExecutorService exec = Executors.newFixedThreadPool(NUM_THREADS);
        // Ex: With 14 items, we want [4, 4, 3, 3] items instead of [3, 3, 3, 5] items
        final int minItemsPerThread = listSize / NUM_THREADS;
        final int maxItemsPerThread = minItemsPerThread + 1;
        final int threadIndicesWithMaxItems = listSize - (minItemsPerThread * NUM_THREADS);
        final List<Future<?>> futures = new ArrayList<>(listSize);
        int startIndex = 0;
        for (int i = 0; i < NUM_THREADS; i++) {
            final int itemCount = i < threadIndicesWithMaxItems ? maxItemsPerThread : minItemsPerThread;
            final int endIndex = startIndex + itemCount;
            final List<?> subList = Collections.synchronizedList(inputList.subList(startIndex, endIndex));
            futures.add(exec.submit(() -> {
                runPerList(subList);
            }));
            startIndex = endIndex;
        }

        for (final Future<?> future : futures) {
            future.get();
        }

        // SHUT DOWN exec. THIS MUST BE after the `get()` call!
        exec.shutdown();
    }

    /**
     * TODO: Edit what to do per list
     *
     * @param inputList
     */
    private void runPerList(final List<?> inputList) {
        LOGGER.info(String.format("List size: %d", inputList.size()));

        for (int i = 0; i < inputList.size(); i++) {
            sleepAndLog();
            // Locking our lock. This should be the last statement ran.
            lockAndIncrementProgress();
        }
    }

    private void sleepAndLog() {
        final Random random = new Random();
        try {
            final double extraSeconds = random.nextDouble();
            final int sleepMillis = 1000 + ((int) (extraSeconds * 1000));
            LOGGER.info(String.format("Sleep for %s milliseconds", sleepMillis));
            Thread.sleep(sleepMillis);
        } catch (final InterruptedException e) {
            LOGGER.error("Exception on sleep!");
        }
        LOGGER.info("Sleep done!");
    }

    private void lockAndIncrementProgress() {
        LOCK.lock();
        try {
            // CRITICAL SECTION
            // Try to minimize code here!
            currentProgress += 1;
            LOGGER.info(getProgressString(currentProgress, listSize));
        } finally {
            LOCK.unlock();
        }
    }
}
