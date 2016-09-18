/*

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.


 */
package design;

import java.util.LinkedList;

/**
 * Created by poorvank on 18/09/16.
 */
public class MinStack {

    private LinkedList<Integer> stack;
    private LinkedList<Integer> smallestNumbers;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new LinkedList<>();
        smallestNumbers = new LinkedList<>();
    }

    public void push(int x) {
        stack.addFirst(x);
        if(!smallestNumbers.isEmpty()) {
            //In case there are 2 equal small numbers
            if(x<=smallestNumbers.getFirst()) {
                smallestNumbers.addFirst(x);
            }
        } else {
            smallestNumbers.addFirst(x);
        }
    }

    public void pop() {
        if(!stack.isEmpty()) {
            int stackTop = stack.removeFirst();
            if(!smallestNumbers.isEmpty()) {
                if(getMin()==stackTop) {
                    smallestNumbers.removeFirst();
                }
            }
        }
    }

    public int top() {
        return stack.getFirst();
    }

    public int getMin() {
        int min = 0;
        if(!smallestNumbers.isEmpty()) {
            min = smallestNumbers.getFirst();
        }

        return min;
    }
}