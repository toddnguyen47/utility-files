package springasyncconfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Reference: <a>https://www.baeldung.com/spring-async</a>
 *
 * Usage:
 * <pre>
 *  @Async(value = SpringAsyncConfig.THREAD_POOL_NAME)
 *  public void functionName() {}
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
        threadPoolTaskExecutor.setCorePoolSize(threadCorePoolSize);
        return threadPoolTaskExecutor;
    }
}
