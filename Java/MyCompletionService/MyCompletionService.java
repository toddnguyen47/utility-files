package MyCompletionService;

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
* Sample usage of `CompletionService`.
*
* Ref: https://stackoverflow.com/a/11872604/6323360
*/
public final class MyCompletionService {
	private static final Logger LOGGER = LogManager.getLogger(MyCompletionService.class);

	public static <T> void executeMultithreaded(
			final int numThreads, final List<Callable<T>> callables) throws IOException {
		// Per channel UUID
		ExecutorService executor = Executors.newFixedThreadPool(numThreads);
		CompletionService<T> completionService = new ExecutorCompletionService<>(executor);

		// Submit tasks
		for (int i = 0; i < callables.size(); i++) {
			Callable<T> callable = callables.get(i);
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
		LOGGER.info("multithreaded execution finished.");
	}
}
