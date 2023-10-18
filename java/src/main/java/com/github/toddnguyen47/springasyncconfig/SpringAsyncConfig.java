package com.github.toddnguyen47.springasyncconfig;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Reference: <a>https://www.baeldung.com/spring-async</a>
 *
 * Usage:
 * <pre>
 *    @Async(value = SpringAsyncConfig.THREAD_POOL_NAME)
 *    public void functionName() {}
 * </pre>
 */
@Configuration
@EnableAsync
public class SpringAsyncConfig {

  public static final String THREAD_POOL_NAME = "myThreadPoolTaskExecutor";

  private final int threadCorePoolSize;

  public SpringAsyncConfig(@Value("${thread.core.pool.size}") final int corePoolSize) {
    this.threadCorePoolSize = corePoolSize;
  }

  @Bean(name = THREAD_POOL_NAME)
  public Executor threadPoolTaskExecutor() {
    final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    // Create a fixed size pool by setting core pool size and max pool size
    // Ref:
    // https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ThreadPoolExecutor.html
    threadPoolTaskExecutor.setCorePoolSize(threadCorePoolSize);
    threadPoolTaskExecutor.setMaxPoolSize(threadCorePoolSize);
    threadPoolTaskExecutor.initialize();
    return threadPoolTaskExecutor;
  }
}
