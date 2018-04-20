/*

A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

 */
package linklist.medium;

/**
 * Created by poorvank.b on 20/04/18.
 */
public class RandomPointer {

    private static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }

    public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null) {
            return null;
        }

        RandomListNode  current = head;

        while(current!=null) {
            RandomListNode newNode = new RandomListNode(current.label);
            RandomListNode tmp =current.next;
            current.next = newNode;
            newNode.next = tmp;
            current = current.next.next;
        }

        current = head;

        while(current!=null) {
            if(current.random!=null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }

        RandomListNode head2 = head.next;
        current = head;

        RandomListNode copy = head2;

        while(current!=null && copy!=null) {
            current.next = current.next!=null?current.next.next:null;
            copy.next = copy.next!=null?copy.next.next:null;
            current = current.next;
            copy = copy.next;
        }

        return head2;
    }

}
