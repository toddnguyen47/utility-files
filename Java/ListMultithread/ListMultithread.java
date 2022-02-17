package multithreadtrial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ListMultithread {

    private static final int NUM_THREADS = 4;
    private static final Logger LOGGER = LogManager.getLogger(ListMultithread.class);

    public static void main(String[] args) {
        final ListMultithread lm = new ListMultithread();
        lm.execute();
    }

    public void execute() {
        final int listSize = 14;
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
        final List<Future<?>> futures = new ArrayList<Future<?>>(listSize);
        int startIndex = 0;
        for (int i = 0; i < NUM_THREADS; i++) {
            final int itemCount = i < threadIndicesWithMaxItems ? minItemsPerThread : maxItemsPerThread;
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
        final Random random = new Random();

        for (int i = 0; i < inputList.size(); i++) {
            try {
                final double extraSeconds = random.nextDouble();
                final int sleepMillis = 1000 + ((int) (extraSeconds * 1000));
                LOGGER.info(String.format("Sleep for %s milliseconds", sleepMillis));
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                LOGGER.error("Exception on sleep!");
            }
        }
        LOGGER.info("Sleep done!");
    }
}
