package careercup.Example.JavaConcurrent;

// Created by zhenlu on 7/21/17.


import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class ConcurrentUtils {

    public static void stop(ExecutorService exec) {
        try {
            exec.shutdown();
            exec.awaitTermination(60, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            if (!exec.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            exec.shutdownNow();
        }
    }

    public static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }
        catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
