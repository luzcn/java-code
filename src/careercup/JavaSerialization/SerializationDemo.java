package careercup.JavaSerialization;

// Created by zhenlu on 6/6/17.


import java.io.File;
import java.util.List;

public class SerializationDemo implements java.io.Serializable {

    private String aString = "The value of that string";
    private int someInteger = 0;

    // But this won't since it is marked as transient.
    private transient List<File> unInterestingLongLongList;


//    public static void main(String[] args) throws IOException {
//
//        SerializationDemo demo = new SerializationDemo();
//
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("o.ser")));
//
//        oos.writeObject(demo);
//
//    }
}
