/*

Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.
Notes:
You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue),
 as long as you use only standard operations of a stack.
You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).

 */
package design.medium;

import java.util.Stack;

/**
 * Created by poorvank on 18/09/16.
 */
class MyQueue {

    Stack<Integer> enqueueStack = new Stack<>();
    Stack<Integer> dequeueStack = new Stack<>();

    // Push element x to the back of queue.
    public void push(int x) {
        enqueueStack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        fillDequeue();

        dequeueStack.pop();
    }

    // Get the front element.
    public int peek() {
        fillDequeue();

        return dequeueStack.peek();
    }

    private void fillDequeue() {
        if (dequeueStack.isEmpty()) {

            while (!enqueueStack.isEmpty()) {
                int x = enqueueStack.pop();
                dequeueStack.push(x);
            }

        }
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return dequeueStack.isEmpty() && enqueueStack.isEmpty();
    }
}
