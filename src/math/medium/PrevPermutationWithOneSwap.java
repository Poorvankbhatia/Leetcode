/*

Given an array A of positive integers (not necessarily distinct),
return the lexicographically largest permutation that is smaller than A,
that can be made with one swap (A swap exchanges the positions of two numbers A[i] and A[j]).  If it cannot be done, then return the same array.



Example 1:

Input: [3,2,1]
Output: [3,1,2]
Explanation: Swapping 2 and 1.
Example 2:

Input: [1,1,5]
Output: [1,1,5]
Explanation: This is already the smallest permutation.
Example 3:

Input: [1,9,4,6,7]
Output: [1,7,4,6,9]
Explanation: Swapping 9 and 7.
Example 4:

Input: [3,1,1,3]
Output: [1,3,1,3]
Explanation: Swapping 1 and 3.


Note:

1 <= A.length <= 10000
1 <= A[i] <= 10000

 */
package math.medium;

public class PrevPermutationWithOneSwap {

    public int[] prevPermOpt1(int[] A) {

        // Find index of the last
        // element of the string
        int n = A.length - 1;

        // Find largest index i such
        // that str[i - 1] > str[i]
        int i = n;
        while (i > 0 && A[i - 1] <= A[i]) {
            i--;
        }

        if (i <= 0) {
            return A;
        }

        int j = i - 1;
        while (j + 1 <= n && A[j + 1] < A[i - 1]) {
            j++;
        }

        // Swap character at i-1 with j
        swap(A, i - 1, j);

        return A;

    }

    private void swap(int[] A,int i, int j) {
        int temp = A[i];
        A[i]=A[j];
        A[j]=temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,1,1,3};
        new PrevPermutationWithOneSwap().prevPermOpt1(arr);
    }

}
