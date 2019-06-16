/*
Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.

Note that elements beyond the length of the original array are not written.

Do the above modifications to the input array in place, do not return anything from your function.



Example 1:

Input: [1,0,2,3,0,4,5,0]
Output: null
Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
Example 2:

Input: [1,2,3]
Output: null
Explanation: After calling your function, the input array is modified to: [1,2,3]


Note:

1 <= arr.length <= 10000
0 <= arr[i] <= 9
 */
package arrays.medium;

public class DuplicateZeroes {
    public void duplicateZeros(int[] A) {
        int n = A.length, i = 0, j = 0;
        for (i = 0; i < n; ++i, ++j) {
            if (A[i] == 0) ++j;
        }
        for (i = i - 1; i >= 0; --i) {
            if (--j < n) {
                A[j] = A[i];
            }
            if (A[i] == 0 && --j < n) {
                A[j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        new DuplicateZeroes().duplicateZeros(new int[]{1,0,2,3,0,4,5,0});
    }

}
