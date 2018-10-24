package leetcode;

import java.util.Iterator;

// Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().
//
// Example:
//
// Assume that the iterator is initialized to the beginning of the list: [1,2,3].
//
// Call next() gets you 1, the first element in the list.
// Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
// You call next() the final time and it returns 3, the last element.
// Calling hasNext() after that should return false.
//
// Thought:
// implement an iterator with peek function,
// so we need to keep a "peek" variable which always pointing the current value
// - when call peek(), directly return this peek value
// - when call next(), we still need to return this peek value,
// but also move the peek to next value if the current iterator still has values available, otherwise set to null
public class PeekingIterator<T> implements Iterator<T> {

  private Iterator<T> current;
  T peek = null;

  public PeekingIterator(Iterator<T> iterator) {
    // initialize any member here.
    current = iterator;
    if (current.hasNext()) {
      peek = current.next();
    }
  }

  // Returns the next element in the iteration without advancing the iterator.
  public T peek() {

    return this.peek;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public T next() {
    T value = peek;

    if (current.hasNext()) {
      peek = current.next();
    } else {
      peek = null;
    }

    return value;
  }

  @Override
  public boolean hasNext() {
    return peek != null;
  }
}
