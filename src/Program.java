import java.util.HashMap;
import java.util.Map;

public class Program {

    public String similarRGB(String color) {

        char[] hex = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        double closest1 = -10000;
        double closest2 = -10000;
        double closest3 = -10000;
        Map<Character, Integer> map = new HashMap<>();
        map.put('0', 0);
        map.put('1', 1);
        map.put('2', 2);
        map.put('3', 3);
        map.put('4', 4);
        map.put('5', 5);
        map.put('6', 6);
        map.put('7', 7);
        map.put('8', 8);
        map.put('9', 9);
        map.put('a', 10);
        map.put('b', 11);
        map.put('c', 12);
        map.put('d', 13);
        map.put('e', 14);
        map.put('f', 15);


        int value1 = map.get(color.charAt(1)) * 16 + map.get(color.charAt(2));
        int value2 = map.get(color.charAt(3)) * 16 + map.get(color.charAt(4));
        int value3 = map.get(color.charAt(5)) * 16 + map.get(color.charAt(6));

        String s1 = "", s2 = "", s3 = "";
        for (char aHex : hex) {
            int v1 = map.get(aHex) * 17;
            if (Math.abs(v1 - value1) < Math.abs(closest1)) {
                closest1 = v1 - value1;
                s1 = "" + aHex + aHex;
            }
        }

        for (char aHex : hex) {
            int v2 = map.get(aHex) * 17;
            if (Math.abs(v2 - value2) < Math.abs(closest2)) {
                closest2 = v2 - value2;
                s2 = "" + aHex + aHex;
            }
        }
        for (char aHex : hex) {
            int v3 = map.get(aHex) * 17;
            if (Math.abs(v3 - value3) < Math.abs(closest3)) {
                closest3 = v3 - value3;
                s3 = "" + aHex + aHex;
            }
        }

        return "#" + s1 + s2 + s3;
    }

    public static void main(String[] args) {
        Program p = new Program();

        System.out.println(p.similarRGB("#09f166"));
    }
}
