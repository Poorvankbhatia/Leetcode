/*

Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.

 */
package linklist.medium;

import linklist.easy.ListNode;

/**
 * Created by poorvank on 06/05/17.
 */
public class PartitionList {

    public ListNode partition(ListNode head, int x) {

        if(head==null) {
            return null;
        }

        ListNode dummy1 = new ListNode(0), dummy2 = new ListNode(0);  //dummy heads of the 1st and 2nd queues
        ListNode curr1 = dummy1, curr2 = dummy2;      //current tails of the two queues;
        while (head!=null){
            if (head.val<x) {
                curr1.next = head;
                curr1 = head;
            }else {
                curr2.next = head;
                curr2 = head;
            }
            head = head.next;
        }
        curr2.next = null;          //important! avoid cycle in linked list. otherwise u will get TLE.
        curr1.next = dummy2.next;
        return dummy1.next;

    }

    public static void main(String[] args) {
        ListNode node = new ListNode(73);
        node.next = new ListNode(1);
        ListNode head = new PartitionList().partition(node,22);
        while (head!=null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

}

/*

the basic idea is to maintain two queues,
the first one stores all nodes with val less than x , and the second queue stores all the rest nodes. Then concat these two queues

 */