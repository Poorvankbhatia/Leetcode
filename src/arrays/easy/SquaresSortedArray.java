/*

Given an array of integers A sorted in non-decreasing order, return an array of the squares of each number, also in sorted non-decreasing order.



Example 1:

Input: [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Example 2:

Input: [-7,-3,2,3,11]
Output: [4,9,9,49,121]


Note:

1 <= A.length <= 10000
-10000 <= A[i] <= 10000
A is sorted in non-decreasing order.

 */
package arrays.easy;

/**
 * Created by poorvank.b on 20/01/19.
 */
public class SquaresSortedArray {

    public int[] sortedSquares(int[] A) {
        int n=A.length;
        int i=0;int j=n-1;
        int[] result = new int[A.length];
        int k =n-1;
        while(i<=j) {
            if(square(A[i])>square(A[j])) {
                result[k]=square(A[i]);
                i++;
            } else {
                result[k]=square(A[j]);
                j--;
            }
            k--;
        }
        return result;

    }

    private int square(int x) {
        return x*x;
    }

}
