/*

Given two arrays of integers with equal lengths, return the maximum value of:

|arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|

where the maximum is taken over all 0 <= i, j < arr1.length.



Example 1:

Input: arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
Output: 13
Example 2:

Input: arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
Output: 20


Constraints:

2 <= arr1.length == arr2.length <= 40000
-10^6 <= arr1[i], arr2[i] <= 10^6

 */
package arrays.medium;

public class MaximumAbsoluteValue {

    public int maxAbsValExpr(int[] arr1, int[] arr2) {
        int n = arr1.length;
        int[] sum1 = new int[n];
        int[] sum2 = new int[n];
        int[] diff1 = new int[n];
        int[] diff2 = new int[n];
        for(int i=0;i<n;i++) {
            sum1[i]=arr1[i]+arr2[i]+i;
            diff1[i]=arr1[i]-arr2[i]+i;
            sum2[i]=arr1[i]+arr2[i]-i;
            diff2[i]=arr1[i]-arr2[i]-i;
        }
        return Math.max(Math.max(util(sum1,n),(util(diff1,n))),Math.max(util(sum2,n), util(diff2,n)));
    }

    private int util(int[] a, int n) {
        int maxSum = a[0], minSum = a[0];
        for (int i = 0; i < n; i++) {
            maxSum=Math.max(maxSum,a[i]);
            minSum=Math.min(minSum,a[i]);
        }
        return (maxSum-minSum);
    }

}

/*

See Images:
1 & 8 ; 2 & 7; 3 & 6; 4 & 5 are practically the same.
So , problem reduces to finding max of (1,2,3,4).
And in each 1,2,3,4, values in both brackets are same, so we simply find max(value in bracket) - min(value in bracket) for each.
Then find max of values obtained from (1,2,3,4)

 */
