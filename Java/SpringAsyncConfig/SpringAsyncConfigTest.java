package com;

import java.util.concurrent.Executor;
import org.junit.Assert;
import org.junit.Test;

public class SpringAsyncConfigTest {

    @Test
    public void test_CreationOfThreadPoolExecutorsIsCorrect() {
        final SpringAsyncConfig springAsyncConfig = new SpringAsyncConfig();

        final Executor executor = springAsyncConfig.threadPoolTaskExecutor();

        Assert.assertNotNull(executor);
    }
}
