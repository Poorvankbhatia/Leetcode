/*

Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.

For example,
Given {1,2,3,4}, reorder it to {1,4,2,3}.

 */
package linklist.medium;

import linklist.easy.ListNode;

/**
 * Created by poorvank on 22/05/17.
 */
public class ReorderList {

    public void reorderList(ListNode head) {

        if(head==null) {
            return;
        }


        ListNode n1 = head, n2 = head;

        // Finding middle of link children.
        while(n2!=null && n2.next!=null){
            n1 = n1.next;
            n2 = n2.next.next;
        }
        ListNode middle = n1, cur = n1.next;
        if(cur!=null){
            ListNode tmp = cur.next;
            cur.next = null;
            cur = tmp;
        }

        // Reversing the right half of the children.
        while(cur!=null){
            ListNode tmp = cur.next;
            cur.next = middle.next;
            middle.next = cur;
            cur = tmp;
        }

        // Merging the two children
        ListNode left = head, right = middle.next;
        while(right!=null){
            middle.next = right.next;
            right.next = left.next;
            left.next = right;
            left = right.next;
            right = middle.next;
        }

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        ListNode current = head;
        while (current!=null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();

        new ReorderList().reorderList(head);

        current = head;
        while (current!=null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }

}

/*

Time Limit exceeding solution 12/13 test cases passed:

Logic is:  every time keep reversing the entire link children, except the first element

public void reorderList(ListNode head) {

        if(head==null) {
            return;
        }

        ListNode current = head,prev= null,next;


        while ( current.next!=null) {

            ListNode last = current;
            current = current.next;

            while (current!=null) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            last.next = prev;
            current = prev;
            prev = null;

        }

    }

 */
