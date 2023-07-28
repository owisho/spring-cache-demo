package com.example.spring.multithread;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolTest {

    @Test
    public void testForkJoinAction() {

        ForkJoinPool pool = ForkJoinPool.commonPool();

        CustomRecursiveAction action = new CustomRecursiveAction("hello world fork join pool");
        pool.execute(action);

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void testForkJoinTask() {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        CustomRecursiveTask task = new CustomRecursiveTask(arr);
        pool.execute(task);
        System.out.println(task.join());

        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
