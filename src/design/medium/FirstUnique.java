/*

You have a queue of integers, you need to retrieve the first unique integer in the queue.

Implement the FirstUnique class:

FirstUnique(int[] nums) Initializes the object with the numbers in the queue.
int showFirstUnique() returns the value of the first unique integer of the queue, and returns -1 if there is no such integer.
void add(int value) insert value to the queue.


Example 1:

Input:
["FirstUnique","showFirstUnique","add","showFirstUnique","add","showFirstUnique","add","showFirstUnique"]
[[[2,3,5]],[],[5],[],[2],[],[3],[]]
Output:
[null,2,null,2,null,3,null,-1]

Explanation:
FirstUnique firstUnique = new FirstUnique([2,3,5]);
firstUnique.showFirstUnique(); // return 2
firstUnique.add(5);            // the queue is now [2,3,5,5]
firstUnique.showFirstUnique(); // return 2
firstUnique.add(2);            // the queue is now [2,3,5,5,2]
firstUnique.showFirstUnique(); // return 3
firstUnique.add(3);            // the queue is now [2,3,5,5,2,3]
firstUnique.showFirstUnique(); // return -1

Example 2:

Input:
["FirstUnique","showFirstUnique","add","add","add","add","add","showFirstUnique"]
[[[7,7,7,7,7,7]],[],[7],[3],[3],[7],[17],[]]
Output:
[null,-1,null,null,null,null,null,17]

Explanation:
FirstUnique firstUnique = new FirstUnique([7,7,7,7,7,7]);
firstUnique.showFirstUnique(); // return -1
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
firstUnique.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
firstUnique.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
firstUnique.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
firstUnique.showFirstUnique(); // return 17

Example 3:

Input:
["FirstUnique","showFirstUnique","add","showFirstUnique"]
[[[809]],[],[809],[]]
Output:
[null,809,null,-1]

Explanation:
FirstUnique firstUnique = new FirstUnique([809]);
firstUnique.showFirstUnique(); // return 809
firstUnique.add(809);          // the queue is now [809,809]
firstUnique.showFirstUnique(); // return -1



Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^8
1 <= value <= 10^8
At most 50000 calls will be made to showFirstUnique and add.

 */
package design.medium;


import java.util.*;

public class FirstUnique {

    private DLLNode head;
    private DLLNode tail;
    public HashMap<Integer,DLLNode> map = new HashMap<>(); // Mapping of values with DLL Nodes.

    private class DLLNode {
        int value;
        DLLNode next;
        DLLNode prev;
        private DLLNode(int value, DLLNode next, DLLNode prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private DLLNode addToList(int x) {
        DLLNode node = new DLLNode(x,null,null);
        if(tail==null) {
            head = tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        return node;
    }

    private void removeFromList(DLLNode node) {
        // Remove only of node !=null
        if(node!=null) {
            if(node.prev!=null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }

            if(node.next!=null) {
                node.next.prev = node.prev;
            } else {
                tail = node.prev;
            }
        }
    }

    public FirstUnique(int[] nums) {
        for (int n : nums) {
            add(n);
        }
    }

    public int showFirstUnique() {
        if(head!=null) {
            return head.value;
        }
        return -1;
    }

    public void add(int value) {
        if(!map.containsKey(value)) {
            map.put(value,addToList(value));
        } else {
            removeFromList(map.get(value));
            map.put(value,null); // Make the value as null so each value is removed only once.
        }
    }
}

/*
Similar Concept To LRU.
 */