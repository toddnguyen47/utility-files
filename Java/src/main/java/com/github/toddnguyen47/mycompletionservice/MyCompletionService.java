package com.github.toddnguyen47.mycompletionservice;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Sample usage of {@code CompletionService} and various other multithreaded functions.
 *
 * You can use either functions; they will both wait until all jobs are completed.
 * NOTE: You must call shutdown() to shutdown the executor after you are done! Otherwise the
 * program will not exit.
 *
 * Ref: https://stackoverflow.com/a/11872604/6323360
 */
public class MyCompletionService {

  public static final byte BYTE_TRUE = 1;
  public static final byte BYTE_FALSE = 0;

  private static final Logger LOGGER = LogManager.getLogger(MyCompletionService.class);

  private final ExecutorService executor;

  public MyCompletionService(final int numThreads) {
    int n1 = numThreads;
    if (n1 <= 0) {
      n1 = 1;
    }
    this.executor = Executors.newFixedThreadPool(n1);
  }

  public void shutdown() {
    try {
      this.executor.awaitTermination(2, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      LOGGER.error("awaitTermination error: {}", e.getMessage());
      Thread.currentThread().interrupt();
    }
    this.executor.shutdown();
  }

  /**
   * Use `CompletionService` to invoke multithread actions
   * @param <T>
   * @param numThreads
   * @param callables
   * @throws IOException
   */
  public <T> void executeWithCompletionService(final List<Callable<T>> callables)
      throws IOException {
    CompletionService<T> completionService = new ExecutorCompletionService<>(this.executor);

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
      throw new IOException(e);
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
  public <T> void invokeAll(final List<Callable<T>> callables) throws IOException {

    try {
      List<Future<T>> results = this.executor.invokeAll(callables);
      LOGGER.debug("results len: {}", results.size());
    } catch (final InterruptedException e) {
      LOGGER.error(e.getMessage());
      throw new IOException(e);
    }
    LOGGER.info("invokeAll() execution finished.");
  }
}
