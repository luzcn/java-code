package design;

import java.util.HashMap;
import java.util.Map;

public class DesignTinyUrl {

  private int index = 1;
  private String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private Map<Integer, String> map = new HashMap<>();

  private int toBase62(char c) {
    if (c >= '0' && c <= '9') {
      return c - '0';
    }

    if (c >= 'a' && c <= 'z') {
      return c - 'a' + 10;
    }

    if (c >= 'A' && c <= 'Z') {
      return c - 'A' + 36;
    }

    return 0;
  }

  // Encodes a URL to a shortened URL.
  public String encode(String longUrl) {

    StringBuilder shortedUrl = new StringBuilder();

    int id = this.index;
    map.put(id, longUrl);

    while (id > 0) {
      shortedUrl.append(this.chars.charAt(id % 62));
      id /= 62;
    }

    while (shortedUrl.length() < 6) {
      shortedUrl.append('0');
    }

    this.index++;
    return shortedUrl.reverse().toString();
  }

  // Decodes a shortened URL to its original URL.
  public String decode(String shortUrl) {
    int id = 0;

    for (char c : shortUrl.toCharArray()) {
      id = id * 62 + toBase62(c);
    }

    return map.get(id);
  }
}
