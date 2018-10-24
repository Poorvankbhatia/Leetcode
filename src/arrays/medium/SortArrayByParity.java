/*

Given an array A of non-negative integers, half of the integers in A are odd, and half of the integers are even.

Sort the array so that whenever A[i] is odd, i is odd; and whenever A[i] is even, i is even.

You may return any answer array that satisfies this condition.



Example 1:

Input: [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.


Note:

2 <= A.length <= 20000
A.length % 2 == 0
0 <= A[i] <= 1000


 */
package arrays.medium;

/**
 * Created by poorvank.b on 24/10/18.
 */
public class SortArrayByParity {

    public int[] sortArrayByParityII(int[] A) {
        int n = A.length;
        int[] sortedArray = new int[n];
        int i=0,j=1;
        for(int a : A) {
            if(a%2==0) {
                sortedArray[i]=a;
                i+=2;
            } else {
                sortedArray[j]=a;
                j+=2;
            }
        }
        return sortedArray;
    }

}

/*

In place:

 public int[] sortArrayByParityII(int[] A) {
        int i = 0, j = 1, n = A.length;
        while (i < n && j < n) {
            while (i < n && A[i] % 2 == 0) {
                i += 2;
            }
            while (j < n && A[j] % 2 == 1) {
                j += 2;
            }
            if (i < n && j < n) {
                swap(A, i, j);
            }
        }
        return A;
    }
    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

 */
