/*

We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)


Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000


 */
package heap.easy;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by poorvank.b on 17/01/19.
 */
public class KClosestPoints {

    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->((b[0]*b[0]+b[1]*b[1])-(a[0]*a[0]+a[1]*a[1])));//Using Max PQ
        for(int i=0;i<K;i++) {
            pq.add(points[i]);
        }
        for(int i=K;i<points.length;i++) {
            int[] peek = pq.peek();
            int currentTop = peek[0]*peek[0]+peek[1]*peek[1];
            int val = (points[i][0]*points[i][0])+(points[i][1]*points[i][1]);
            if(currentTop>val) {
                pq.poll();
                pq.add(points[i]);
            }
        }
        int[][] res = new int[pq.size()][2];
        int i=0;
        while(!pq.isEmpty()) {
            int[] x = pq.poll();
            res[i++]=x;
        }
        return res;
    }

}

/*


Using Quick Select:

public int[][] kClosest(int[][] points, int K) {
    int len =  points.length, l = 0, r = len - 1;
    while (l <= r) {
        int mid = helper(points, l, r);
        if (mid == K) break;
        if (mid < K) {
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    return Arrays.copyOfRange(points, 0, K);
}

private int helper(int[][] A, int l, int r) {
    int[] pivot = A[l];
    while (l < r) {
        while (l < r && compare(A[r], pivot) >= 0) r--;
        A[l] = A[r];
        while (l < r && compare(A[l], pivot) <= 0) l++;
        A[r] = A[l];
    }
    A[l] = pivot;
    return l;
}

private int compare(int[] p1, int[] p2) {
    return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
}


In the quick sort, we will always choose a pivot to compare with other elements.
After one iteration, we will get an array that all elements smaller than the pivot are on the left side of the pivot and
all elements greater than the pivot are on the right side of the pviot (assuming we sort the array in ascending order).
So, inspired from this, each iteration, we choose a pivot and then find the position p the pivot should be.
Then we compare p with the K, if the p is smaller than the K, meaning the all element on the left of the pivot are all proper candidates but it is not adequate,
we have to do the same thing on right side, and vice versa.
If the p is exactly equal to the K, meaning that we've found the K-th position. Therefore, we just return the first K elements, since they are not greater than the pivot.

" The time complexity of quick select is O(n)" and also the difference between quick sort and quick select.
Now, it think the difference located in the recursion part.
In quick sort, we need to do recursion in each part, but in quick select,
we can throw the one of the part and do the recursion in the rest of the part.
Therefore, the time complexity of quick select is T(t) = 2T(t) - T(t) = c(2n + n + n/2 + n/4 + ... + 2) - c *(n + n/2 + n/4 + n/8 + ... + 1) = 2cn -1 = O(n).

The advantage of this solution is it is very efficient.
The disadvatage of this solution are it is neither an online solution nor a stable one. And the K elements closest are not sorted in ascending order.


 */
