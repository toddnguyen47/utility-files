package com;

import java.util.concurrent.Executor;
import org.junit.Assert;
import org.junit.Test;

public class SpringAsyncConfigTest {

    private static final int CORE_POOL_SIZE = 4;

    @Test
    public void test_CreationOfThreadPoolExecutorsIsCorrect() {
        final SpringAsyncConfig springAsyncConfig = new SpringAsyncConfig(CORE_POOL_SIZE);

        final Executor executor = springAsyncConfig.threadPoolTaskExecutor();

        Assert.assertNotNull(executor);
    }
}
