/*

Implement a Max Stack， which supports peekMax() and popMax()

 */
package design.easy;

import java.util.Stack;

/**
 * Created by poorvank.b on 13/11/17.
 */
public class MaxStack {
    private Stack<Integer> stack;
    private Stack<Integer> maxStack;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        pushHelper(x);
    }

    private void pushHelper(int x) {
        int tempMax = maxStack.isEmpty() ? Integer.MIN_VALUE : maxStack.peek();
        if (x > tempMax) {
            tempMax = x;
        }
        stack.push(x);
        maxStack.push(tempMax);
    }

    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = maxStack.peek();
        Stack<Integer> temp = new Stack<>();

        while (stack.peek() != max) {
            temp.push(stack.pop());
            maxStack.pop();
        }
        stack.pop();
        maxStack.pop();
        while (!temp.isEmpty()) {
            int x = temp.pop();
            pushHelper(x);
        }
        return max;
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

class MaxStack {
    TreeMap<Integer, List<Node>> map;
    DoubleLinkedList dll;

    public MaxStack() {
        map = new TreeMap();
        dll = new DoubleLinkedList();
    }

    public void push(int x) {
        Node node = dll.add(x);
        if(!map.containsKey(x))
            map.put(x, new ArrayList<Node>());
        map.get(x).add(node);
    }

    public int pop() {
        int val = dll.pop();
        List<Node> L = map.get(val);
        L.remove(L.size() - 1);
        if (L.isEmpty()) map.remove(val);
        return val;
    }

    public int top() {
        return dll.peek();
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = peekMax();
        List<Node> L = map.get(max);
        Node node = L.remove(L.size() - 1);
        dll.unlink(node);
        if (L.isEmpty()) map.remove(max);
        return max;
    }
}

class DoubleLinkedList {
    Node head, tail;

    public DoubleLinkedList() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }

    public Node add(int val) {
        Node x = new Node(val);
        x.next = tail;
        x.prev = tail.prev;
        tail.prev = tail.prev.next = x;
        return x;
    }

    public int pop() {
        return unlink(tail.prev).val;
    }

    public int peek() {
        return tail.prev.val;
    }

    public Node unlink(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return node;
    }
}

class Node {
    int val;
    Node prev, next;
    public Node(int v) {val = v;}
}


Algorithm

Let's store the stack as a double linked list dll, and store a map from value to a List of Node.

When we MaxStack.push(x), we add a node to our dll, and add or update our entry map.get(x).add(node).

When we MaxStack.pop(), we find the value val = dll.pop(), and remove the node from our map, deleting the entry if it was the last one.

When we MaxStack.popMax(), we use the map to find the relevant node to unlink, and return it's value.

The above operations are more clear given that we have a working DoubleLinkedList class.
 The implementation provided uses head and tail sentinels to simplify the relevant DoubleLinkedList operations.

 O(logN) for all operations except peek which is O(1), where N
  is the number of operations performed. Most operations involving TreeMap are O(logN).

 */