/*

A sequence X_1, X_2, ..., X_n is fibonacci-like if:

n >= 3
X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
Given a strictly increasing array A of positive integers forming a sequence, find the length of the longest fibonacci-like
 subsequence of A.  If one does not exist, return 0.

(Recall that a subsequence is derived from another sequence A by deleting any number of elements (including none) from A,
 without changing the order of the remaining elements.  For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)



Example 1:

Input: [1,2,3,4,5,6,7,8]
Output: 5
Explanation:
The longest subsequence that is fibonacci-like: [1,2,3,5,8].
Example 2:

Input: [1,3,7,11,12,14,18]
Output: 3
Explanation:
The longest subsequence that is fibonacci-like:
[1,11,12], [3,11,14] or [7,11,18].

 */
package hashing.medium;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank.b on 22/07/18.
 */
public class FibonacciSubSequence {

    public int lenLongestFibSubseq(int[] A) {

        int n = A.length;
        Set<Integer> set = new HashSet<>();
        for (int a : A) {
            set.add(a);
        }
        int res=0;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                int a = A[i];
                int b = A[j];
                int ans=2;
                while (set.contains(a+b)) {
                    int temp = b;
                    b=a+b;
                    a=temp;
                    ans++;
                }
                res = Math.max(res,ans);
            }
        }
        return res==2?0:res;

    }

}

/*

Time Complexity: O(N^2 log M) where N is the length of A, and M is the maximum value of A.
Since the values grow exponentially,
the amount of numbers needed to accommodate a sequence
that ends in a number M is at most log(M).

 */
