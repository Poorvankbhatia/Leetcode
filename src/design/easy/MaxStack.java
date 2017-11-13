/*

Implement a Max Stack， which supports peekMax() and popMax()

 */
package design.easy;

import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by poorvank.b on 13/11/17.
 */
public class MaxStack<T> {

    private LinkedList<T> stack;
    private PriorityQueue<T> maxHeap;

    public MaxStack() {
        stack = new LinkedList<>();
        maxHeap = new PriorityQueue<T>(10, Collections.reverseOrder());
    }

    public void push(T n) {
        stack.addLast(n);
        maxHeap.offer(n);
    }

    public T pop() {
        T num = stack.removeLast();
        maxHeap.remove(num);
        return num;
    }

    public T top() {
        return stack.peekLast();
    }

    public T peekMax() {
        return maxHeap.peek();
    }

    public T popMax() {
        T num = maxHeap.poll();
        stack.remove(num);
        return num;
    }

}

/*

This problem is more complicated than min stack, becuase we have to support popMax() method which will alter the structure of the stack.

For instance, stack = {1, 3, 2, 4, 6, 5}, as usual, maxStack = {1, 3, 4, 6}. If we only need to support peekMax(),
then we can simply use two stacks as what we did in Min Stack. But if we call popMax(), the stack becomes stack = {1, 3, 2, 4, 5},
maxStack = {1, 3, 4}. In this case, the new max value should be 5 instead of 4.

One naive method is to pop until we meet the max value and then push back other elements and update maxStack at the same time. For instance,

stack = {1, 3, 2, 4, 6, 5}, maxStack = {1, 3, 4, 6}
stack = {1, 3, 2, 4, 6}, maxStack = {1, 3, 4, 6}
stack = {1, 3, 2, 4}, maxStack = {1, 3, 4}, we have pop out the max value
push back. stack = {1, 3, 2, 4, 5}, maxStack = {1, 3, 4, 5}, update max stack at the same time.
However, in the worst case, popMax() method can take O(n) time. push and pop methods only take O(1) time.

We can instead use a doubly linkedList to maintain the normal stack. And then use a max-heap to maintain the order of the stack.
In this implementation. push and pop methods take O(logn) time, since we have to update the heap. However, peekMax() and popMax()
method only takes O(1) time, since we only need to delete the max value in the heap and delete it in the doubly linkedList.


push, pop, popMax: O(logn)

peekMax, top: O(1)

 */