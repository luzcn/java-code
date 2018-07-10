import java.util.*;

import careercup.Lyft.DesignExcel;

public class Program {

    public static void main(String[] args) {
        // A1=B1+C1
        // B1 = C2 - D1
        // C2 = 1
        // D1 = -1
        // C1 = 12

        List<String> expressions = new ArrayList<>();

        expressions.add("A1=B1+C1");
        expressions.add("B1=C2-D1");
        expressions.add("C2=1");
        expressions.add("C1=12");

        DesignExcel ms = new DesignExcel(expressions);

        for (String exp : expressions) {
            ms.compute(exp);
        }
    }
}