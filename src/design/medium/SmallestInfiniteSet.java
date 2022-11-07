/*
You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].

Implement the SmallestInfiniteSet class:

SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
int popSmallest() Removes and returns the smallest integer contained in the infinite set.
void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.


Example 1:

Input
["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
[[], [2], [], [], [], [1], [], [], []]
Output
[null, null, 1, 2, 3, null, 1, 4, 5]

Explanation
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
                                   // is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.


Constraints:

1 <= num <= 1000
At most 1000 calls will be made in total to popSmallest and addBack.
 */
package design.medium;

import java.util.HashSet;
import java.util.PriorityQueue;

public class SmallestInfiniteSet {

    // smallest integer which has never been popped.
    private int smallestNotPopped;
    // integers which have been added back, will be ordered.
    private PriorityQueue<Integer> addedBack;
    // Integers which have been popped.
    private HashSet<Integer> popped;
    public SmallestInfiniteSet() {
        smallestNotPopped = 1;
        addedBack = new PriorityQueue<>();
        popped = new HashSet<>();
    }

    public int popSmallest() {
        // initialize answer to the smallest element not yet popped.
        int ans = smallestNotPopped;
        // if addedBack contains elements and has an element smaller than the smallestNotPopped
        // that would be the answer eg : popSmallest(1) popSmallest(2) popSmallest(3),
        // smallestNotPopped=4; addBack(3) .. since 3<4 return 3;
        if(!addedBack.isEmpty() && smallestNotPopped> addedBack.peek()) {
            ans = addedBack.poll();
        } else {
            // increment only when a new element in popped.
            smallestNotPopped++;
        }
        // add to the pop set.
        popped.add(ans);
        return ans;
    }

    public void addBack(int num) {
        // if already popped
        if(popped.contains(num)) {
            // remove from pop.
            popped.remove(num);
            // add to pq.
            addedBack.add(num);
        }
    }

    public static void main(String[] args) {
        SmallestInfiniteSet si = new SmallestInfiniteSet();
        System.out.println(si.popSmallest());
        System.out.println(si.popSmallest());
        System.out.println(si.popSmallest());
        si.addBack(2);
        si.addBack(1);
        System.out.println(si.popSmallest());
        si.addBack(3);
        si.addBack(4);
        si.addBack(1);
        System.out.println(si.popSmallest());
        si.addBack(4);
        System.out.println(si.popSmallest());
        System.out.println(si.popSmallest());

    }

}
