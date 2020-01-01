/*

A sorted list A contains 1, plus some number of primes.  Then, for every p < q in the list, we consider the fraction p/q.

What is the K-th smallest fraction considered?  Return your answer as an array of ints, where answer[0] = p and answer[1] = q.

Examples:
Input: A = [1, 2, 3, 5], K = 3
Output: [2, 5]
Explanation:
The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
The third fraction is 2/5.

Input: A = [1, 7], K = 1
Output: [1, 7]
Note:

A will have length between 2 and 2000.
Each A[i] will be between 1 and 30000.
K will be between 1 and A.length * (A.length - 1) / 2.


 */
package binarysearch.hard;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created by poorvank.b on 23/02/18.
 */

public class KthSmallestPrimeFraction {

    int p,q;
    private int[] countPairs(int[] A, double x) {
        int count = 0, n = A.length, p = 0, q = 0;
        double max = 0.0;
        for (int i = 0; i < n; i++) {
            int j=0;
            while (j < i && A[j] < A[i] * x) {
                j++;
            }
            if (j > 0) {
                double fraction = (double)A[j-1] / (double)A[i];
                if (max < fraction) {
                    max = fraction;
                    p = A[j-1];
                    q = A[i];
                }
            }
            count += j;
        }
        return new int[] {count, p, q};
    }

    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        int n = A.length, min = A[0], max = A[n-1];
        p=0; q=0;
        double lo = (double)min/(double)max, hi = 1.0;
        while (lo < hi) {
            double mid = (lo + hi) / 2.0;
            int[] count = countPairs(A, mid);
            if (count[0] == K) {
                p = count[1];
                q = count[2];
                break;
            }
            if (count[0] < K) {
                lo = mid;
            }
            else {
                hi = mid;
            }
        }
        return new int[] {p, q};
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,5};
        System.out.println(Arrays.toString(new KthSmallestPrimeFraction().kthSmallestPrimeFraction(arr,3)));
    }

}

/*

This solution probably doesn’t have the best runtime but it’s really simple and easy to understand.

Says if the children is [1, 7, 23, 29, 47], we can easily have this table of relationships

1/47  < 1/29    < 1/23 < 1/7
7/47  < 7/29    < 7/23
23/47 < 23/29
29/47
So now the problem becomes “find the kth smallest element of (n-1) sorted children”

Following is my implementation using PriorityQueue, running time is O(max(n,k) * logn), space is O(n):

// DO READ
https://leetcode.com/problems/k-th-smallest-prime-fraction/discuss/115819/Summary-of-solutions-for-problems-%22reducible%22-to-LeetCode-378


  |  7    5    2    1
--+-------------------
1 | 1/7  1/5  1/2  1/1
2 | 2/7  2/5  2/2  2/1
5 | 5/7  5/5  5/2  5/1
7 | 7/7  7/5  7/2  7/1

Priority Queue:

public int[] kthSmallestPrimeFraction(int[] a, int k) {

        int n = a.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            int s1 = a[o1[0]] * a[o2[1]];
            int s2 = a[o2[0]] * a[o1[1]];
            return s1 - s2;
        });
        for (int i = 0; i < n-1; i++) {
            pq.add(new int[]{i, n-1});
        }
        for (int i = 0; i < k-1; i++) {
            int[] pop = pq.remove();
            int num = pop[0];
            int den = pop[1];
            if (den - 1 > num) {
                pop[1]--;
                pq.add(pop);
            }
        }

        int[] peek = pq.peek();
        return new int[]{a[peek[0]], a[peek[1]]};


    }


 */