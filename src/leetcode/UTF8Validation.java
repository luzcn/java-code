package leetcode;

// A character in UTF8 can be from "1 to 4 bytes long", subjected to the following rules:
//
// - For 1-byte character, the first bit is a 0, followed by its unicode code.

// - For n-bytes character, the first n-bits are all one's, the n+1 bit is 0,
// followed by n-1 bytes with most significant 2 bits being 10.
//
// This is how the UTF-8 encoding would work:
//
//    Char. number range  |        UTF-8 octet sequence
//       (hexadecimal)    |              (binary)
//    --------------------+---------------------------------------------
//    0000 0000-0000 007F | 0xxxxxxx
//    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
//    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
//    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
// Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
//
// Note:
// The input is an array of integers. Only the least significant 8 bits of each integer is used to store the data.
// This means each integer represents only 1 byte of data.
//
// Example 1:
//
// data = [197, 130, 1], which represents the octet sequence: 11000101 10000010 00000001.
//
// Return true.
// It is a valid utf-8 encoding for a 2-bytes character followed by a 1-byte character.
// Example 2:
//
// data = [235, 140, 4], which represented the octet sequence: 11101011 10001100 00000100.
//
// Return false.
// The first 3 bits are all one's and the 4th bit is 0 means it is a 3-bytes character.
// The next byte is a continuation byte which starts with 10 and that's correct.
// But the second continuation byte does not start with 10, so it is invalid.
public class UTF8Validation {


  private int getByteNumbers(int data) {
    int count = 0;
    for (int i = 7; i >= 0; i--) {
      if (((data >> i) & 1) == 1) {
        count++;
      } else {
        break;
      }
    }

    return count;
  }

  public boolean validUtf8(int[] data) {

    if (data == null || data.length == 0) {
      return false;
    }

    int begin = 0;
    int length = data.length;

    while (begin < length) {
      int bytes = getByteNumbers(data[begin]);

      if (bytes == 1) {
        // if character is 1 byte, the first bit is 0,
        // if character is n byte, the first n bits should be 1
        return false;
      }

      if (bytes == 0) {
        // one byte character, need to check next
        begin++;
        continue;
      }

      if (bytes == 2) {
        if (begin + 1 > length || getByteNumbers(data[begin + 1]) != 1) {
          return false;
        }
        begin += 2;
      } else if (bytes == 3) {
        if (begin + 2 > length || getByteNumbers(data[begin + 1]) != 1
            || getByteNumbers(data[begin + 2]) != 1) {
          return false;
        }
        begin += 3;
      } else if (bytes == 4) {
        if (begin + 3 > length || getByteNumbers(data[begin + 1]) != 1
            || getByteNumbers(data[begin + 2]) != 1
            || getByteNumbers(data[begin + 3]) != 1) {
          return false;
        }
        begin += 4;
      } else {
        return false;
      }

    }
    return true;
  }
}
