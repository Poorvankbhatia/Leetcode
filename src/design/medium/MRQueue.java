/*

Design a queue-like data structure that moves the most recently used element to the end of the queue.

Implement the MRUQueue class:

MRUQueue(int n) constructs the MRUQueue with n elements: [1,2,3,...,n].
int fetch(int k) moves the kth element (1-indexed) to the end of the queue and returns it.


Example 1:

Input:
["MRUQueue", "fetch", "fetch", "fetch", "fetch"]
[[8], [3], [5], [2], [8]]
Output:
[null, 3, 6, 2, 2]

Explanation:
MRUQueue mRUQueue = new MRUQueue(8); // Initializes the queue to [1,2,3,4,5,6,7,8].
mRUQueue.fetch(3); // Moves the 3rd element (3) to the end of the queue to become [1,2,4,5,6,7,8,3] and returns it.
mRUQueue.fetch(5); // Moves the 5th element (6) to the end of the queue to become [1,2,4,5,7,8,3,6] and returns it.
mRUQueue.fetch(2); // Moves the 2nd element (2) to the end of the queue to become [1,4,5,7,8,3,6,2] and returns it.
mRUQueue.fetch(8); // The 8th element (2) is already at the end of the queue so just return it.


Constraints:

1 <= n <= 2000
1 <= k <= n
At most 2000 calls will be made to fetch.


Follow up: Finding an O(n) algorithm per fetch is a bit easy. Can you find an algorithm with a better complexity for each fetch call?

 */
package design.medium;

class MRUQueue {
    static class Node {
        int val;
        Node next;
        Node prev;

        public Node(int v) {
            val = v;
        }
    }

    private static int step = 50; // alternatively it could be Math.sqrt(n)
    Node[] skipNodes;
    Node head;
    int MRUQueueLength;

    public MRUQueue(int n) {
        MRUQueueLength = n;
        int m = n / step;
        skipNodes = new Node[m];
        head = new Node(0);
        Node cur = head;
        int idx = 0;
        int j = 1;
        for (int i = n; i > 0; i--, j++) {
            Node next = new Node(i);
            cur.next = next;
            next.prev = cur;
            if (j == step) {
                skipNodes[idx++] = next;
                j = 0;
            }
            cur = next;
        }
    }

    public int fetch(int k) {
        int index = 0;
        int nodeIndex = MRUQueueLength - k + 1; // our list has reverse order
        Node cur = head;
        while(nodeIndex >= step) {
            nodeIndex -= step;
            cur = skipNodes[index];
            skipNodes[index] = skipNodes[index].prev;
            index++;
        }

        while(nodeIndex > 0) {
            cur = cur.next;
            nodeIndex--;
        }

        // delete cur from Linked List
        if (cur.next != null) cur.next.prev = cur.prev;
        cur.prev.next = cur.next;
        // insert cur after the head
        cur.next = head.next;
        if (head.next != null) head.next.prev = cur;
        cur.prev = head;
        head.next = cur;

        return cur.val;
    }
}
/*

The idea with a linked list is straight forward and It was already described here
But it is O(n).
To not iterate through the whole list, we can store every Mth node and jump to them.
M could be some constant, like in the code below, or depend on N, like: int m = n > 25 ? (int) Math.sqrt(n) : 25.
In case of sqrt value, fetch operation would have sqrt complexity (2 * sqrt(N) operations at max). For example, for
N = 100, we can store links to each 10th node, then in worth case we need to perform 18 operations
(10->20->...->90->91->...->99).
Since we are moving the fetched node to the tail, indexes will be changed over time and we need to update stored nodes.
I found it is easier to do with reversed doubly linked list. In such case every time a stored node is accessed,
it is replaced with the previose one (node.prev).

Example:
Input:
["MRUQueue", "fetch", "fetch", "fetch", "fetch"]
[[8], [3], [5], [2], [8]]
Output:
[null, 3, 6, 2, 2]
Operations:

MRUQueue - 8
we'll be storing each 3rd element, step = 3;
list: head->8->7->6->5->4->3->2->1
index: -----1---2---3--4---5---6--7---8
stored:-------------6-----------3;
Fetch - 3
nodeIndex - 6 (8-3+1)
a) 6 >= 3, jump to the fisrt stored element (6) and since we'll be inserting before it, we are storing previose node (7)
b) 6 >= 6, jump to the second stored element (3) and since we'll be inserting before it, we are storing previose node (4)
c) 6th node is found, we are removing it from the current position and inserting after the head
list: head->3->8->7->6->5->4->2->1
stored:-------------7-----------4;
result - 3
Fetch - 6
nodeIndex - 3 (8-6+1)
a) 6 >= 3, jump to the fisrt stored element (7) and since we'll be inserting before it, we are storing previose node (8)
b) 3rd node is found, we are removing it from the current position and inserting after the head
list: head->7->3->8->6->5->4->2->1
stored:-------------8-----------4;
result - 7
Fetch - 2
nodeIndex - 7 (8-2+1)
a) 6 >= 3, jump to the fisrt stored element (8) and since we'll be inserting before it, we are storing previose node (3)
b) 6 >= 6, jump to the second stored element (4) and since we'll be inserting before it, we are storing previose node (5)
c) iterating to the next element (2)
d) 7th node is found, we are removing it from the current position and inserting after the head
list: head->2->7->3->8->6->5->4->1
stored:-------------3-----------5;
result - 2
Fetch - 8
nodeIndex - 1 (8-8+1)
a) getting the node next to the head, removing it from the current position and inserting after the head
list: head->2->7->3->8->6->5->4->1
stored:-------------3-----------5;
result - 2

 */