/*

Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.

 */
package design.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 18/09/16.
 */
class MyStack {
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();
    // Push element x onto stack.
    public void push(int x) {
        q2.add(x);

        while (!q1.isEmpty()) {
            int pops = q1.remove();
            q2.add(pops);
        }

        Queue<Integer> temp = new LinkedList<>();
        temp = q1;
        q1 = q2;
        q2 = temp;


    }

    // Removes the element on top of the stack.
    public void pop() {
        q1.remove();
    }

    // Get the top element.
    public int top() {
        return q1.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q2.isEmpty() && q1.isEmpty();
    }
}
