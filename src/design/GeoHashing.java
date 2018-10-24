package design;

public class GeoHashing {

  private String geoHasingBase32 = "0123456789bcdefghjkmnpqrstuvwxyz";
  private String result = "";

  public String getHashing(double latitude, double longitude, int precision) {

    search(latitude, -90, 90, "", 5 * precision / 2);
    String latitudeCode = result;

    search(longitude, -180, 180, "", 5 * precision / 2);
    String longitudeCode = result;

    // interleave the latitude and longitude
    // take longitude first
    StringBuilder code = new StringBuilder();

    for (int i = 0; i < latitudeCode.length(); i++) {
      code.append(longitudeCode.charAt(i)).append(latitudeCode.charAt(i));
    }

    StringBuilder geoCode = new StringBuilder();
    for (int i = 0; i < code.length(); i += 5) {
      // int index = String.format(code.substring(i, i + 5), );
      int index = Integer.valueOf(code.substring(i, i + 5), 2);

      geoCode.append(geoHasingBase32.charAt(index));
    }

    return geoCode.toString();
  }

  private void search(double value, double left, double right, String code, int length) {
    if (code.length() == length) {
      result = code;
      return;
    }

    double mid = left + (right - left) / 2;

    if (value > mid) {
      search(value, mid, right, code + "1", length);
    } else {
      search(value, left, mid, code + "0", length);
    }
  }
}
