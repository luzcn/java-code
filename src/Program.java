import leetcode.AllOOneDataStructure;

public class Program {

    public static void main(String[] args) {
        AllOOneDataStructure ds = new AllOOneDataStructure();

        ds.inc("h");
        ds.inc("w");
        ds.inc("h");
        ds.dec("w");

        ds.inc("h");
        ds.inc("leet");

        System.out.println(ds.getMaxKey());

        ds.dec("h");
        ds.dec("h");
        ds.dec("h");


        System.out.println(ds.getMaxKey());
    }
}

// ["AllOne","inc","inc","inc","dec","inc","inc","getMaxKey","dec","dec","dec","getMaxKey"]
//    [[],["hello"],["world"],["hello"],["world"],["hello"],["leet"],[],["hello"],["hello"],["hello"],[]]