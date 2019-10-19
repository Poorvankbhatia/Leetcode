/*

In some array arr, the values were in arithmetic progression: the values arr[i+1] - arr[i] are all equal for every 0 <= i < arr.length - 1.

Then, a value from arr was removed that was not the first or last value in the array.

Return the removed value.



Example 1:

Input: arr = [5,7,11,13]
Output: 9
Explanation: The previous array was [5,7,9,11,13].
Example 2:

Input: arr = [15,13,12]
Output: 14
Explanation: The previous array was [15,14,13,12].


Constraints:

3 <= arr.length <= 1000
0 <= arr[i] <= 10^5

 */
package binarysearch.easy;

public class FindMissingNo {
    public int missingNumber(int[] arr) {
        int n = arr.length;
        int difference = (arr[n - 1] - arr[0]) / n;
        return util(arr, 0, n - 1, difference);
    }

    private int util(int[] arr, int low, int high, int diff) {
        // There must be two elements
        // to find the missing
        if (high <= low)
            return 0;

        // Middle Element.
        int mid = low + (high - low) / 2;

        if (arr[mid + 1] - arr[mid] != diff)
            return (arr[mid] + diff);


        if (mid > 0 && arr[mid] - arr[mid - 1] != diff)
            return (arr[mid - 1] + diff);

        // Elements till mid follow progression.
        if (arr[mid] == arr[0] + mid * diff)
            return util(arr, mid + 1, high, diff);

        // Else recur for left half
        return util(arr, low, mid - 1, diff);
    }
}
