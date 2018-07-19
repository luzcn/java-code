package careercup.Lyft;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class KeyValueStore {

    public static void main(String[] args) {

        // To read from stdin:
        InputStream source = System.in;

        KeyValueStore keyValueStore = new KeyValueStore();

        /*
        Or, to read from a file:
        InputStream source = new FileInputStream(filename);

        Or, to read from a network stream:
        InputStream source = socket.getInputStream();
        */

        Scanner in = new Scanner(source);
        while (in.hasNext()) {
            String input = in.nextLine(); // Use in.nextLine() for line-by-line reading

            // Assume the input test case is always valid
            String[] parsedInput = input.split(" ");
            if (parsedInput[0].equals("PUT")) {
                int version = keyValueStore.put(parsedInput[1], Integer.parseInt(parsedInput[2]));

                System.out.println("PUT(#" + version + ") " + parsedInput[1] + " = " + parsedInput[2]);
            } else if (parsedInput[0].equals("GET")) {
                int value = 0;

                if (parsedInput.length == 2) {
                    value = keyValueStore.get(parsedInput[1]);
                    System.out.println("GET " + parsedInput[1] + " = " + value);

                } else {
                    value = keyValueStore.get(parsedInput[1], Integer.parseInt(parsedInput[2]));
                    System.out.println("GET " + parsedInput[1] + "(#" + parsedInput[2] + ")" + " = " + value);
                }

            }

            // Process the input here. For example, you could print it out:
            // System.out.println(input);
        }
    }

    // the global version number, declared as staci member variable
    // the first version is 1
    // will increase every time with write operation
    private static int version = 1;

    // a hashmap of key and set of <value, version> pair
    // use sorted set to help query version
    private HashMap<String, TreeSet<VersionedValue>> mapKeyVersionedValue;


    // constructor
    public KeyValueStore() {

        this.mapKeyVersionedValue = new HashMap<>();
    }


    /**
     * The put operation will increase the global version number.
     *
     * the get-then-set pattern on global version number may introduce race condition
     * use synchronized keyword to make this function thread safe
     *
     * @param key the key of input key-value data
     * @param value the value of input key-value data
     * @return the version number which assigned to this key-value pair
     */
    public synchronized int put(String key, int value) {

        int currentVersion = version++;

        mapKeyVersionedValue.computeIfAbsent(key, k -> new TreeSet<>()).add(new VersionedValue(value, currentVersion));

        return currentVersion;
    }


    /**
     * @param key the key of key-value data
     * @return the value of last version
     */
    public Integer get(String key) {
        if (this.mapKeyVersionedValue.get(key) == null) {
            return null;
        }

        return mapKeyVersionedValue.get(key).last().value;
    }


    /**
     * get the value of given key with version, if the key has no value with the given number, return the closest version value
     *
     * @param key the query key
     * @param version the query version number
     * @return the value with given version number or the closest version number
     */
    public Integer get(String key, int version) {
        if (this.mapKeyVersionedValue.get(key) == null || version < 1) {
            return null;
        }

        // get the sorted set of "VersionedValue" objects which have  version number smaller or equals given query version
        // the Java TreeSet.headSet need to provide a "VersionedValue" object, I create a dummy VersionedValue object with value 0
        // set the headSet inclusive to true to get the object with equal versions
        SortedSet<VersionedValue> valueSet = mapKeyVersionedValue.get(key)
                .headSet(new VersionedValue(0, version), true);

        return valueSet.last().value;
    }


    /**
     * A helper class to save the value and version pair
     */
    private class VersionedValue implements Comparable<VersionedValue> {

        int value;
        int version;

        // constructor
        VersionedValue(int value, int version) {
            this.value = value;
            this.version = version;
        }

        @Override
        public int compareTo(VersionedValue value) {
            return version - value.version;
        }
    }
}
