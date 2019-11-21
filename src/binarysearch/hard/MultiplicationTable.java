/*

find out the k-th smallest number quickly from the multiplication table?

Given the height m and the length n of a m * n Multiplication Table, and a positive integer k,
you need to return the k-th smallest number in this table.

Example 1:
Input: m = 3, n = 3, k = 5
Output:
Explanation:
The Multiplication Table:
1	2	3
2	4	6
3	6	9

The 5-th smallest number is 3 (1, 2, 2, 3, 3).
Example 2:
Input: m = 2, n = 3, k = 6
Output:
Explanation:
The Multiplication Table:
1	2	3
2	4	6

The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
Note:
The m and n will be in the range [1, 30000].
The k will be in the range [1, m * n]

 */
package binarysearch.hard;

/**
 * Created by poorvank.b on 22/10/17.
 */
public class MultiplicationTable {

    public int findKthNumber(int m, int n, int k) {
        int lo = 1;
        int hi = m*n;

        while(lo<hi) {
            int mid = lo+(hi-lo)/2;
            int count = less(mid,m,n);
            /*
            Do not return when count == k, the reason of we have hi = mid instead of to directly return mid,
            is that the mid could be not in the multiplication table.
            And for c > k, we still keep the mid, and try a smaller value in [low, mid]
            is that there could exist duplicate numbers in the multiplication table when calculating the count.
             */
            if(count>=k) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }
        return lo;

    }

    private int less(int mid,int m,int n) {
        int count = 0;
        int i=1;
        int j=n;
        while(i<=m && j>=0) {
            if(i*j<=mid) {
                count+=j;
                i++;
            } else {
                j--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new MultiplicationTable().findKthNumber(9895,28405,100787757)); // Solution 31666344
    }

}

/*

Time complexity : O(m∗log(m∗n))


So why the smallest candidate is in M table?
Because if the smallest candidate(no smaller than k numbers in M table), saying x, is not in M table,
then x-1 will also be a candidate(no smaller than k numbers in M table) since x is not in the table.
Then x is not the smallest candidate.
For example, we have such range in our flattern sorted M table:

Val: 1,..., xi,  xi, xj,  xj,  ...
Seq: 1,..., p-1, p,  p+1, p+2, ...
When k=p, {xi, xi+1,..., xj-1} are all valid candidates. There are p numbers no larger than xi or xi+1or xj-1 because {xi+1,..., xj-1} are not in M table.
And we need to return xi which is in the M table and the smallest candidate as well.
When k=p+1, the smallest candidate would be xj.

As for time complexity, it would be log(mn) times of binary search so total is O(mlog(mn)).
And we can swap m, n if m > n and ensure time complexity to be O(min(m,n) * log(mn))

==========

Since K is in range of (m*n) i.e 9*10^8

The key point for any binary search is to figure out the "Search Space". T
here are two kind of "Search Space" -- index and range(the range from the smallest number to the biggest number).
Most usually, when the array is sorted in one direction, we can use index as "search space",
when the array is unsorted and we are going to find a specific number, we can use "range".

two examples of these two "search space"

index -- https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ ( the array is sorted)
range -- https://leetcode.com/problems/find-the-duplicate-number/ (Unsorted Array)
The reason why we did not use index as "search space" for this problem is the matrix is sorted in two directions,
we can not find a linear way to map the number and its index.

Heap Method: Runs in O(k∗mlogm)=O(square(m)nlogm).
 Our initial heapify operation is O(m).
Afterwards, each pop and push is O(mlogm), and our outer loop is O(k) = O(m*n)

    private class Element implements Comparable<Element> {

        Integer rowNo;
        Integer colNo;
        Integer val;

        public Element(int rowNo, int colNo, int val) {
            this.rowNo = rowNo;
            this.colNo = colNo;
            this.val = val;
        }

        @Override
        public int compareTo(Element o) {
            return this.val.compareTo(o.val);
        }
    }

    public int findKthNumber(int m, int n, int k) {

        MinPriorityQueue<Element> minPriorityQueue = new MinPriorityQueue<>(m);

        for (int i=1;i<=m;i++) {
            minPriorityQueue.insert(new Element(i,1,i));
        }

        int kthSmallest=0;

        for (int i=0;i<k;i++) {
            Element e = minPriorityQueue.deleteMin();
            kthSmallest= e.val;

            int nextCol = e.colNo+1;

            if(nextCol>n) {
                e = new Element(e.rowNo,nextCol,Integer.MAX_VALUE);
            } else {
                e = new Element(e.rowNo,nextCol,calculateValue(e.rowNo,nextCol));
            }
            minPriorityQueue.insert(e);

        }

        return kthSmallest;

    }

    private int calculateValue(int m,int n) {
        return (m*n);
    }

 */