package com.zhenming.demo.application.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class CompletableFutureTest {
    private static final Logger LOG = LoggerFactory.getLogger(CompletableFutureTest.class);

    @Test
    void completableFutureTest() {
        CompletableFuture<String> completableFuture = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        TimeUnit.NANOSECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "[completableFuture-1] - 死磕 Java 新特性";
                })
                .whenComplete((res, ex) -> {
                    if (ex == null) {
                        LOG.info("结果是：{}", res);
                    } else {
                        LOG.info("发生了异常，异常信息是：{}", ex.getMessage());
                    }
                });
        IntStream.range(0, 5).map(i -> 5 - i).mapToObj(String::valueOf).forEach(s -> {
            try {
                TimeUnit.NANOSECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            LOG.info("{}", s);
        });
        completableFuture.join();
        Assertions.assertTrue(true);
    }
}
