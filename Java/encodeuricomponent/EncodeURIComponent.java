package encodeuricomponent;

import java.io.IOException;
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
* You can use either functions; they will both wait until all jobs are completed.
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
      int callablesSize = callables.size();
      for (int i = 0; i < callablesSize; i++) {
        Callable<T> callable = callables.get(i);
        completionService.submit(callable);
      }

      // Wait for tasks to complete
      try {
        for (int i = 0; i < callablesSize; i++) {
          Future<T> future = completionService.take();
          T result = future.get();
          LOGGER.debug("Result: {}", result);
        }
      } catch (final InterruptedException | ExecutionException e) {
        LOGGER.error(e.getMessage());
        Thread.currentThread().interrupt();
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
