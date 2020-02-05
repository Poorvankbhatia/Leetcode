/*

Given an integer array arr and a target value target, return the integer value such that when we change all the integers
larger than value in the given array to be equal to value, the sum of the array gets as close as possible (in absolute difference) to target.

In case of a tie, return the minimum such integer.

Notice that the answer is not neccesarilly a number from arr.



Example 1:

Input: arr = [4,9,3], target = 10
Output: 3
Explanation: When using 3 arr converts to [3, 3, 3] which sums 9 and that's the optimal answer.
Example 2:

Input: arr = [2,3,5], target = 10
Output: 5
Example 3:

Input: arr = [60864,25176,27249,21296,20204], target = 56803
Output: 11361


Constraints:

1 <= arr.length <= 10^4
1 <= arr[i], target <= 10^5

 */
package binarysearch.medium;

public class MutatedArrayClosestToTarget {

    public int findBestValue(int[] arr, int target) {
        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int a : arr) {
            sum += a;
            max = Math.max(max, a);
        }
        if (sum == target) {
            return max;
        }
        int min = 0, res = 1,diff = Integer.MAX_VALUE;
        // The answer would lie between 0 and maximum value in the array.
        while (min <= max) {
            int mid = min + (max - min) / 2;
            sum = getMutatedSum(arr, mid);
            if (sum > target) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
            // If current difference is less than diff || current difference==diff but mid < res.(choose the smaller one.)
            if (Math.abs(sum - target) < diff || (Math.abs(sum - target) == diff && mid < res)) {
                res = mid;
                diff = Math.abs(sum - target);
            }
        }
        return res;
    }

    private int getMutatedSum(int[] arr, int mid) {
        int sum = 0;
        for (int a : arr) {
            sum += (a > mid) ? mid : a;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new MutatedArrayClosestToTarget().findBestValue(new int[]{10,20,21,40,59},100));
    }

}


//O(NlogMax(A))

/*

Same Binary search as IsSubsequence/ maximumSide length Square.

 */
