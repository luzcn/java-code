package design;

import java.util.*;

public class SingletonDemo {

    public SingletonDemo() {
        EagerInitializationSingleton instance = EagerInitializationSingleton.getInstance();
    }
}


// eager initialization, the instance of Singleton Class is created at the time of class loading
class EagerInitializationSingleton {

    private static final EagerInitializationSingleton instance = new EagerInitializationSingleton();

    // make the constructor private
    private EagerInitializationSingleton() {
    }

    public static EagerInitializationSingleton getInstance() {

        return instance;
    }
}

// similar to eager initialization, use static block initialization to catch some exceptions
// Both eager initialization and static block initialization creates the instance even before it’s being used
// and that is not the best practice to use
class StaticBlockSingleton {

    private static final StaticBlockSingleton instance;

    // make constructor private
    private StaticBlockSingleton() {
    }

    //static block initialization for exception handling
    static {
        try {
            instance = new StaticBlockSingleton();
        } catch (Exception e) {
            throw new RuntimeException("Exception occured in creating singleton instance");
        }
    }

    public static StaticBlockSingleton getInstance() {
        return instance;
    }
}


class LazyInitializedSingleton {

    private static LazyInitializedSingleton instance;

    private LazyInitializedSingleton() {
    }

    public static LazyInitializedSingleton getInstance() {
        if (instance == null) {
            instance = new LazyInitializedSingleton();
        }

        return instance;
    }
}

// All singletons above are not thread safe
// a simplest way to make it thread safe is use "synchronized" key word to protect the method call
class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    }

    // each time get instance will be locked,
    // the performance is not good
    public static synchronized ThreadSafeSingleton getInstance() {

        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }

        return instance;
    }
}


// we can use double-checking lock
class ThreadSafeSingleton2 {

    private static ThreadSafeSingleton2 instance;

    private ThreadSafeSingleton2() {
    }

    // the synchronized block is used inside the if condition with an additional check
    // to ensure that only one instance of singleton class is created.
    public static synchronized ThreadSafeSingleton2 getInstance() {

        if (instance == null) {

            synchronized (ThreadSafeSingleton2.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton2();
                }
            }
        }

        return instance;
    }
}
