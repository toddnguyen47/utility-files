package com.github.toddnguyen47.springasyncconfig;

import java.util.concurrent.Executor;
import org.junit.Assert;
import org.junit.Test;

public class SpringAsyncConfigTest {

    private static final int THREAD_CORE_POOL_SIZE = 4;

    @Test
    public void test_CreationOfThreadPoolExecutorsIsCorrect() {
        final SpringAsyncConfig springAsyncConfig = new SpringAsyncConfig(THREAD_CORE_POOL_SIZE);

        final Executor executor = springAsyncConfig.threadPoolTaskExecutor();

        Assert.assertNotNull(executor);
    }
}
