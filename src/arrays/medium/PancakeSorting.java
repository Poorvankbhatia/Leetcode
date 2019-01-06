/*

Given an array A, we can perform a pancake flip: We choose some positive integer k <= A.length,
then reverse the order of the first k elements of A.  We want to perform zero or more pancake flips
(doing them one after another in succession) to sort the array A.

Return the k-values corresponding to a sequence of pancake flips that sort A.  Any valid answer that sorts the array within
10 * A.length flips will be judged as correct.



Example 1:

Input: [3,2,4,1]
Output: [4,2,4,3]
Explanation:
We perform 4 pancake flips, with k values 4, 2, 4, and 3.
Starting state: A = [3, 2, 4, 1]
After 1st flip (k=4): A = [1, 4, 2, 3]
After 2nd flip (k=2): A = [4, 1, 2, 3]
After 3rd flip (k=4): A = [3, 2, 1, 4]
After 4th flip (k=3): A = [1, 2, 3, 4], which is sorted.
Example 2:

Input: [1,2,3]
Output: []
Explanation: The input is already sorted, so there is no need to flip anything.
Note that other answers, such as [3, 3], would also be accepted.


Note:

1 <= A.length <= 100
A[i] is a permutation of [1, 2, ..., A.length]

 */
package arrays.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 06/01/19.
 */
public class PancakeSorting {

    public List<Integer> pancakeSort(int[] A) {
        List<Integer> result = new ArrayList<>();
        if(A==null || A.length==0) {
            return result;
        }
        int n = A.length;
        for(int i=n-1;i>=0;i--) {

            int maxIndex = findMax(A,i);
            if(maxIndex!=i) {

                if(maxIndex!=0) {
                    result.add(maxIndex+1);
                    rev(A,maxIndex);
                }

                result.add(i+1);
                rev(A,i);

            }
        }
        return result;
    }

    private int findMax(int[] array, int n) {
        int maxIndex = 0;
        for (int i = 1; i <= n; i++) {
            if (array[maxIndex] < array[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void rev(int[] array, int i) {
        int start = 0;
        while (start < i) {
            int temp = array[start];
            array[start] = array[i];
            array[i] = temp;
            start++;
            i--;
        }
    }

}
