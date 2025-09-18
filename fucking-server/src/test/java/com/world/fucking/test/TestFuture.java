package com.world.fucking.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Slf4j
@SuppressWarnings("all")
public class TestFuture {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch(Thread.currentThread().getName());
        stopWatch.start();
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            futures.add(completableFuture);
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size() ])).join();
        stopWatch.stop();
        log.info(stopWatch.toString());
    }

    public static void testA() throws InterruptedException {
        StopWatch stopWatch = new StopWatch(Thread.currentThread().getName());
        stopWatch.start();
        for (int i = 0; i < 100; i++) {
            Thread.sleep(20);
        }
        stopWatch.stop();
        log.info(stopWatch.toString());
    }
}
