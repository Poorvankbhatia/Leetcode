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
        List<Integer> list = new ArrayList<>();
        for(int end = A.length - 1; end >= 0; end--){
            int largest = findLargest(A, end);
            if(largest == end) {
                continue;
            }
            swap(A, largest);
            swap(A, end);
            list.add(largest + 1);
            list.add(end + 1);
        }
        return list;
    }
    private int findLargest(int[]A, int lastIdx){
        int max = Integer.MIN_VALUE, index = 0;
        for(int i = 0; i <= lastIdx; i++){
            if(A[i] > max){
                max = A[i];
                index = i;
            }
        }
        return index;
    }
    private void swap(int[] A, int end){
        int start = 0;
        while(start < end){
            int tmp = A[0];
            A[start++] = A[end];
            A[end--] = tmp;
        }
    }

    public static void main(String[] args) {
        System.out.println(new PancakeSorting().pancakeSort(new int[]{3,2,4,1}));
    }

}
