package MyCompletionService;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
* Sample usage of {@code CompletionService} and various other multithreaded functions.
*
* Ref: https://stackoverflow.com/a/11872604/6323360
*/
public final class MyCompletionService {
  private static final Logger LOGGER = LogManager.getLogger(MyCompletionService.class);

  private MyCompletionService() {}

  /**
  * Use `CompletionService` to invoke multithread actions
  * @param <T>
  * @param numThreads
  * @param callables
  * @throws IOException
  */
  public static <T> void executeWithCompletionService(
      final int numThreads, final List<Callable<T>> callables) throws IOException {
    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    CompletionService<T> completionService = new ExecutorCompletionService<>(executor);

    // Submit tasks
    Iterator<Callable<T>> iter = callables.iterator();
    while (iter.hasNext()) {
      Callable<T> callable = iter.next();
      completionService.submit(callable);
    }
    executor.shutdown();

    // Wait for tasks to complete
    try {
      while (!executor.isTerminated()) {
        Future<T> future = completionService.take();
        T results = future.get();
        LOGGER.debug(results);
      }
    } catch (final InterruptedException | ExecutionException e) {
      for (final StackTraceElement elem : e.getStackTrace()) {
        LOGGER.error(elem);
      }
    }
    LOGGER.info("executeWithCompletionService() execution finished.");
  }

  /**
  * Use `invokeAll()` to invoke multithread actions
  * @param <T>
  * @param numThreads
  * @param callables
  * @throws IOException
  */
  public static <T> void invokeAll(final int numThreads, final List<Callable<T>> callables)
      throws IOException {

    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    try {
      List<Future<T>> results = executor.invokeAll(callables);
      LOGGER.debug("results len: {}", results.size());
    } catch (final InterruptedException e) {
      for (final StackTraceElement elem : e.getStackTrace()) {
        LOGGER.error(elem);
      }
      throw new IOException(e);
    }
    executor.shutdown();
    LOGGER.info("invokeAll() execution finished.");
  }
}
