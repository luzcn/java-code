package careercup.Example.JavaConcurrent;

// Created by zhenlu on 7/21/17.


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizeExample {
    private int count = 0;

    private ReentrantLock lock = new ReentrantLock();

    private void increment() {
        lock.lock();
        try {
            ++count;
        }
        finally {
            lock.unlock();
        }
    }

    public void RunInThread1() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 10000; i++) {
            executor.submit(this::increment);
        }

        ConcurrentUtils.stop(executor);
        System.out.println(count);  // 9996, race condition
    }


}
