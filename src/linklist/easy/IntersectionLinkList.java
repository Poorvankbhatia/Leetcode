/*

Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.

 */
package linklist.easy;

/**
 * Created by poorvank on 24/02/17.
 */
public class IntersectionLinkList {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if(null==headA || null==headB) {
            return null;
        }

        int lengthA = getLength(headA);
        int lengthB = getLength(headB);

        int diff = Math.abs(lengthA-lengthB);

        if(lengthA>lengthB) {
            return getNode(headA,headB,diff);
        } else {
            return getNode(headB,headA,diff);
        }



    }

    private int getLength(ListNode node) {

        int length = 0;

        while (null!=node) {
            node = node.next;
            length++;
        }

        return length;

    }

    private ListNode getNode(ListNode headA,ListNode headB,int d) {

        ListNode c1 = headA;
        ListNode c2 = headB;

        for (int i=0;i<d;i++) {
            if(c1==null) {
                return null;
            }
            c1 = c1.next;
        }

        while (c1!=null && c2!=null) {
            if(c1.val==c2.val) {
                return c1;
            }
            c1= c1.next;
            c2= c2.next;
        }

        return null;

    }

}

/*

1) Get count of the nodes in first list, let count be c1.
2) Get count of the nodes in second list, let count be c2.
3) Get the difference of counts d = abs(c1 – c2)
4) Now traverse the bigger list from the first node till d nodes so that from here onwards both the lists have equal no of nodes.
5) Then we can traverse both the lists in parallel till we come across a common node. (Note that getting a common node is done by
comparing the address of the nodes)

Time Complexity: O(m+n)
Auxiliary Space: O(1)

Another method:

1. Traverse the first linked list(count the elements) and make a circular linked list. (Remember last node so that we can break the circle later on).
2. Now view the problem as find the loop in the second linked list. So the problem is solved.
3. Since we already know the length of the loop(size of first linked list) we can traverse those many number of nodes in second list, and
then start another pointer from the beginning of second list. we have to traverse until they are equal, and that is the required intersection point.
4. remove the circle from the linked list.

 */