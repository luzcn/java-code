package datastructure.Array;

public class CircularBuffer<T> {

  private int capacity;
  private T[] buffer;
  private int index;

  public CircularBuffer(int cap) {
    this.capacity = cap;
    buffer = (T[]) new Object[cap];
    index = 0;
  }

  public void append(T data) {
    buffer[index] = data;
    index = (index + 1) % capacity;
  }


  public void printAll() {
    for (int i = index; i < index + capacity; i++) {
      System.out.println(buffer[i % capacity]);
    }
  }
}