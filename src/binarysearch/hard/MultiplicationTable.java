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

        int low = 1;
        int high = calculateValue(m,n);

        while (low<high) {
            int mid = (low)+((high-low)/2);
            int count = count(m,n,mid);
            if(count<k) {
                low=mid+1;
            } else {
                high = mid;
            }
        }

        return low;

    }

    //counting how many values are less than or equal to target
    private int count(int m,int n,int target) {
        int i=m;
        int j=1;
        int count=0;

        while (i>=1 && j<=n) {
            if(calculateValue(i,j)<=target) {
                count+=i;
                j++;
            } else {
                i--;
            }
        }

        return count;

    }

    private int calculateValue(int m,int n) {
        return (m*n);
    }

    public static void main(String[] args) {
        System.out.println(new MultiplicationTable().findKthNumber(9895,28405,100787757)); // Solution 31666344
    }

}

/*

Time complexity : O(m∗log(m∗n))

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