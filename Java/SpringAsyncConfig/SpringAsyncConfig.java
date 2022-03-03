package springasyncconfig;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Reference: <a>https://www.baeldung.com/spring-async</a>
 */
@Configuration
@EnableAsync
public class SpringAsyncConfig {

    public static final String THREAD_POOL_NAME = "myThreadPoolTaskExecutor";

    @Bean(name = THREAD_POOL_NAME)
    public Executor threadPoolTaskExecutor() {
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        return threadPoolTaskExecutor;
    }
}
