/*

Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.

Follow up:
What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently without using extra space?

Example:

// Init a singly linked list [1,2,3].
ListNode head = new ListNode(1);
head.next = new ListNode(2);
head.next.next = new ListNode(3);
Solution solution = new Solution(head);

// getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
solution.getRandom();


 */
package linklist.medium;

import linklist.ListNode;

import java.util.Random;

/**
 * Created by poorvank.b on 18/08/18.
 */
public class RandomNodeLinkList {

    private class Solution {

        ListNode head = null;
        Random random = null;
        public Solution(ListNode head) {
            this.head = head;
            this.random = new Random();

        }

        /** Returns a random node's value. */
        public int getRandom() {
            ListNode result = null;
            ListNode current = head;

            for(int n = 1; current!=null; n++) {
                if (random.nextInt(n) == 0) {
                    result = current;
                }
                current = current.next;
            }

            return result==null?0:result.val;

        }

    }

}

/*

O(N) time O(1) space

 */