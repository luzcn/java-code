import design.DesignTinyUrl;

public class Program {

    public static void main(String[] args) {

        DesignTinyUrl dt = new DesignTinyUrl();

        String url = "http://www.google.com/abc";

        System.out.println(dt.decode(dt.encode(url)));
        System.out.println(dt.decode(dt.encode(url)));
        System.out.println(dt.decode(dt.encode(url)));
        System.out.println(dt.decode(dt.encode(url)));

    }
}