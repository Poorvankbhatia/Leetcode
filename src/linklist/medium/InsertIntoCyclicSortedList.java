/*

Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list
such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be
necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value.
After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single cyclic list and return
the reference to that single node. Otherwise, you should return the original given node.

 */
package linklist.medium;

/**
 * Created by poorvank.b on 31/07/18.
 */
public class InsertIntoCyclicSortedList {

    class Node {
        public int val;
        public Node next;

        public Node() {}

        public Node(int _val,Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newNode = new Node(insertVal, null);
            newNode.next = newNode;
            return newNode;
        }

        Node curr = head;
        boolean inserted = false;

        do {
            if (insertHere(curr, insertVal)) {
                curr.next = new Node(insertVal, curr.next);
                inserted = true;
            }

            curr = curr.next;
        } while (curr != head && !inserted);

        if (curr == head) {
            curr.next = new Node(insertVal, curr.next);
            inserted = true;
        }

        return head;
    }

    private boolean insertHere(Node curr, int insertVal) {
        return (curr.val <= insertVal && curr.next.val >= insertVal)
                || ((curr.next.val < curr.val) && (insertVal <= curr.next.val || insertVal >= curr.val));
    }

    public static void main(String[] args) {
        InsertIntoCyclicSortedList insertIntoCyclicSortedList = new InsertIntoCyclicSortedList();
        Node head = insertIntoCyclicSortedList.insert(null,2);
        insertIntoCyclicSortedList.insert(head,1);
        insertIntoCyclicSortedList.insert(head,3);
    }

}
/*

for any sorted integer list and any integer value, there will always be a place to insert the value such that the list remains sorted.

You know you've reached the node before the point of insertion of value x if one of these conditions is met:

The current value is less than x and the next value is greater than x
The next value is less than the current value (i.e. you'v reached the "end" of the sorted listed and are wrapping back to beginning).
 In this case, you would insert x if it's the biggest value (x >= current node value) or the smallest value (x <= next node value).
If you iterate over the entire list once and still haven't found a point of insertion, then the list must contain just 1 value,
so insert the new value anywhere.

 */