package careercup.Salesforce;

// sort a given stack without using any for/while loop operations
// only use recursion

import java.util.Stack;

// pseudo code:
//
// sortStack(stack S)
//     if stack is not empty:
//         temp = pop(S);
//         sortStack(S);
//         sortedInsert(S, temp);
//
// sortedInsert(Stack S, element)
//     if stack is empty OR element > top element
//         push(S, elem)
//     else
//         temp = pop(S)
//         sortedInsert(S, element)
//         push(S, temp)

public class SortStack {

  public void sort(Stack<Integer> stack) {
    if (stack.isEmpty()) {
      return;
    }

    int value = stack.pop();
    sort(stack);
    insertationSort(stack, value);
  }

  private void insertationSort(Stack<Integer> stack, int value) {

    if (stack.isEmpty() || value >= stack.peek()) {
      stack.push(value);
      return;
    }

    int temp = stack.pop();
    insertationSort(stack, value);
    stack.push(temp);
  }
}
