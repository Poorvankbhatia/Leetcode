/*

Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there are no such sequences.

After doing so, return the head of the final linked list.  You may return any such answer.



(Note that in the examples below, all sequences are serializations of ListNode objects.)

Example 1:

Input: head = [1,2,-3,3,1]
Output: [3,1]
Note: The answer [1,2,1] would also be accepted.
Example 2:

Input: head = [1,2,3,-3,4]
Output: [1,2,4]
Example 3:

Input: head = [1,2,3,-3,-2]
Output: [1]


Constraints:

The given linked list will contain between 1 and 1000 nodes.
Each node in the linked list has -1000 <= node.val <= 1000.

 */
package linklist.medium;

import linklist.ListNode;

import java.util.HashMap;

public class RemoveNodesThatSumToZero {
    public ListNode removeZeroSumSublists(ListNode head) {
        // Remove starting zeroes.
        while (head != null && head.val == 0) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        HashMap<Integer, ListNode> map = new HashMap<>();
        ListNode temp = head;
        map.put(0, null);
        map.put(head.val, head);
        int val = head.val;
        temp = temp.next;

        while (temp != null) {
            val += temp.val;
            if (val == 0) {
                head = temp.next;
            } else if (map.containsKey(val)) {
                ListNode node = map.get(val);
                node.next = temp.next;
            } else {
                map.put(val, temp);

            }
            temp = temp.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        System.out.println(new RemoveNodesThatSumToZero().removeZeroSumSublists(listNode));
    }

}

/*
Wrong solution for :
This solution is incorrect for this case [1,3,2,-3,-2,5,5,-5,1], it returns [1,5,5,-5,1] whereas it should be [1,5,1]

Brute force works:

public ListNode removeZeroSumSublists(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode i = dummy;
        while(i != null){
            int sum = 0;
            for(ListNode j = i.next; j != null ;){
                sum += j.val;
                if(sum == 0){
                    i.next = j.next;
                }
                j = j.next;
            }
            i = i.next;
        }
        return dummy.next;
    }

 */