/*

A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

Return the length of a maximum size turbulent subarray of A.



Example 1:

Input: [9,4,2,10,7,8,8,1,9]
Output: 5
Explanation: (A[1] > A[2] < A[3] > A[4] < A[5])
Example 2:

Input: [4,8,12,16]
Output: 2
Example 3:

Input: [100]
Output: 1

 */
package arrays.medium;

/**
 * Created by poorvank.b on 20/01/19.
 */
public class LongestTurbulentArray {

    public int maxTurbulenceSize(int[] A) {
        int inc = 1, dec = 1, result = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i] < A[i - 1]) {
                dec = inc + 1;
                inc = 1;
            } else if (A[i] > A[i - 1]) {
                inc = dec + 1;
                dec = 1;
            } else {
                inc = 1;
                dec = 1;
            }
            result = Math.max(result, Math.max(dec, inc));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new LongestTurbulentArray().maxTurbulenceSize(new int[]{9,4,2,10,7,8,8,1,9}));
    }

}

/*

Similar to wiggle subsequence

inc: The length of sequence which ends with two incresing numbers
dec: The length of sequence which ends with two decreasing numbers


Another way:

A(k)<A(k-1) and k==odd || A(k)>A(k-1) and k==even

A(k)<A(k-1) and k==even || A(k)>A(k-1) and k==odd

 public int maxTurbulenceSize(int[] A) {
        int n = A.length;
        int[] even = new int[n];
        int[] odd = new int[n];
        Arrays.fill(even, 1);
        Arrays.fill(odd, 1);
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < n ; i++) {
            if(A[i]>A[i-1]) {
                odd[i]=even[i-1]+1;
            } else if(A[i]<A[i-1]) {
                even[i]=odd[i-1]+1;
            }
            max = Math.max(max,Math.max(odd[i],even[i]));
        }
        return max==Integer.MIN_VALUE?1:max;
    }

 */
