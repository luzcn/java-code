package careercup.Example.CallbackExample;

// Created by zhenlu on 6/5/17.


public interface Callback {

    void call();

    class CallbackFunction implements Callback {

        @Override
        public void call() {
            System.out.println("This is the callback function.");
        }
    }
}
