package leetcode;

// The API: int read4(char *buf) reads 4 characters at a time from a file.
//
// The return value is the actual number of characters read. For example,
// it returns 3 if there is only 3 characters left in the file.
//
// By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
//
// Example 1:
//
// Input: buf = "abc", n = 4
// Output: "abc"
// Explanation: The actual number of characters read is 3, which is "abc".
// Example 2:
//
// Input: buf = "abcde", n = 5
// Output: "abcde"
public class ReadNCharactersGivenRead4 {

  // helper API function, not really useful
  private int read4(char[] buff) {
    return 0;
  }

  // read 4 chars to a temporary buffer with has size 4
  // copy each read char into the given buffer and increase the current buffer index.
  // if the index reaches required size n or the read to the end of file
  public int read(char[] buf, int n) {

    char[] temp = new char[4];
    int index = 0;

    while (index < n) {

      int res = read4(temp);

      for (int i = 0; i < res && index < n; i++) {
        buf[index++] = temp[i];
      }

      // end of the file, stop reading
      if (res < 4) {
        break;
      }
    }

    return index;
  }

  // private int buffPtr = 0;
  // private int buffCnt = 0;
  // private char[] buff = new char[4];
  // public int read(char[] buf, int n) {
  //     int ptr = 0;
  //     while (ptr < n) {
  //         if (buffPtr == 0) {
  //             buffCnt = read4(buff);
  //         }
  //         if (buffCnt == 0) break;
  //         while (ptr < n && buffPtr < buffCnt) {
  //             buf[ptr++] = buff[buffPtr++];
  //         }
  //         if (buffPtr >= buffCnt) buffPtr = 0;
  //     }
  //     return ptr;
  // }

  // 158. Read N Characters Given Read4 II - Call multiple times
  // The read function may be called multiple times.

  // the start position need to read characters from tempBuff
  private int offset;

  // indicate how may characters in the tempBuff have not been read to "buf"
  private int bufferSize;

  // used to save the read4 characters
  private char[] tempBuff = new char[4];

  public int readMultiple(char[] buf, int n) {
    if (n <= 0) {
      return 0;
    }

    // the count number, how many characters have been read to buf
    int index = 0;

    while (index < n) {
      // there are unprocessed characters from previous "read" call
      // so, no need to call "read4" immediately.
      if (offset == 0) {
        // read chars from file
        bufferSize = read4(tempBuff);
      }

      while (index < n && offset < bufferSize) {
        buf[index++] = tempBuff[offset++];
      }

      // finished read all chars from buf
      // reset the start reading position to 0
      if (offset >= bufferSize) {
        offset = 0;
      }

      // reach the end of file
      // stop reading
      if (bufferSize < 4) {
        break;
      }
    }

    return index;
  }
}
