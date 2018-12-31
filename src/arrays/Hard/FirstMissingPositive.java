/*

Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

 */

package arrays.Hard;

import java.util.Arrays;

/**
 * Created by poorvank on 09/01/17.
 */
public class FirstMissingPositive {


    public int firstMissingPositive(int[] arr) {
        int n = arr.length;
        for (int k=0; k<n; k++) {
            while (arr[k] != k+1 && arr[k]-1 >= 0 && arr[k] <= n && arr[k] != arr[arr[k]-1]) {
                swap(arr, k, arr[k]-1);
            }
        }

        System.out.println(Arrays.toString(arr));

        for (int i=0; i<n; i++) {
            if (arr[i] != i+1)    return i+1;
        }
        return n + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,4,-1,1};
        System.out.print(new FirstMissingPositive().firstMissingPositive(arr));
    }

}

/*

Since the problem requires for linear time complexity and constant space. We can think of using bucket sort idea.
The idea is to check if the index i store the value i + 1, if not swap the the value A[i] to its desired index. After that,
we iterate again the array and check the first position that i != A[i] + 1.

The take-away message for this question is when the question asks for O(n) time solution, and you found that it must be beneficial to sort the
input array, bucket sort is a way to think about.

Note that in the "worst case" scenario for 1 run over the while loop above, all other elements are now in the right order,
and all other runs over the while loop will be free. This will be the case for any initial configuration.
Each swap puts one element in the right place, and this can at most happen n times in the entire for-loop.
If one run over the while loop runs into the worst case scenario of making all these swaps, all other runs have no swaps left to do.


A naive approach is first to sort the array. Then it is quite straightforward to solve the problem. Just compare each element with the result,
if the difference is larger than 1, return the result + 1, else update the result as A[i]. If iterate the entire array and we did not find the
result, return the last element of the array plus 1.

public class Solution {
    public int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0) {
            return 1;
        }

        Arrays.sort(A);
        int result = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] <= 0) {
                continue;
            }

            if (A[i] - result > 1) {
                return result + 1;
            } else {
                result = A[i];
            }
        }
        return A[A.length - 1] + 1;
    }
}


 */