/*

Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array.



Example 1:

Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]


Constraints:

1 <= arr.length <= 10^4
1 <= arr[i] <= 10^5

 */
package arrays.easy;

public class ReplaceElements {

    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        result[n-1]=-1;
        int rightMax = arr[n-1];
        for(int i=n-2;i>=0;i--) {
            result[i]=rightMax;
            rightMax = Math.max(rightMax,arr[i]);
        }
        return result;
    }

}
