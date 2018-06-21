/*

Let's call an array A a mountain if the following properties hold:

A.length >= 3
There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].

Example 1:

Input: [0,1,0]
Output: 1
Example 2:

Input: [0,2,1,0]
Output: 1
Note:

3 <= A.length <= 10000
0 <= A[i] <= 10^6
A is a mountain, as defined above.

 */
package binarysearch.easy;

/**
 * Created by poorvank.b on 20/06/18.
 */
public class PeakMountainArray {

    public int peakIndexInMountainArray(int[] A) {


        int low= 0;
        int high = A.length-1;

        while (low<high) {
            if (high == (low + 1)) {
                return (A[high] > A[low]) ? high : low;
            }

            if (low < high) {

                int mid = low + (high - low) / 2;

                if ((mid == 0 || A[mid] >= A[mid - 1]) && (mid == A.length - 1 || A[mid] >= A[mid + 1])) {
                    return mid;
                } else if (A[mid] > A[mid + 1] && A[mid] < A[mid - 1]) {
                    high=mid;
                } else {
                    low=mid;
                }

            }
        }

        return -1;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{24,69,100,99,79,78,67,36,26,19};
        System.out.println(new PeakMountainArray().peakIndexInMountainArray(arr));
    }

}
