package careercup.CallbackExample;

import java.util.List;
import java.util.function.Function;

// Created by zhenlu on 6/5/17.

public class CallbackExample {

    public void runTask(Callback callback) {
        doTask();

        callback.call();
    }


    private void doTask() {
        System.out.println("this is the main task.");
    }
}
