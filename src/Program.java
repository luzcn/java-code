import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


public class Program {

    public static void main(String[] args) {

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        List<Integer> res = new ArrayList<>();

        map.computeIfPresent(1, (k, v) -> {
            if (v > 0) {
                res.add(k);
            }
            return v - 1;
        });

        System.out.println(map.get(1));

    }

}
