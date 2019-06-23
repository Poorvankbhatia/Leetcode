/*
You may recall that an array A is a mountain array if and only if:

A.length >= 3
There exists some i with 0 < i < A.length - 1 such that:
A[0] < A[1] < ... A[i-1] < A[i]
A[i] > A[i+1] > ... > A[A.length - 1]
Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target.  If such an index doesn't exist, return -1.

You can't access the mountain array directly.  You may only access the array using a MountainArray interface:

MountainArray.get(k) returns the element of the array at index k (0-indexed).
MountainArray.length() returns the length of the array.
Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer.  Also, any solutions that attempt to circumvent the
judge will result in disqualification.



Example 1:

Input: array = [1,2,3,4,5,3,1], target = 3
Output: 2
Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
Example 2:

Input: array = [0,1,2,4,2,1], target = 3
Output: -1
Explanation: 3 does not exist in the array, so we return -1.


Constraints:

3 <= mountain_arr.length() <= 10000
0 <= target <= 10^9
0 <= mountain_arr.get(index) <= 10^9
 */
package binarysearch.medium;


public class MountainArr {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length();
        int r = n - 1;
        int l = 0;
        int peak = -1;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        if (mountainArr.get(l) < mountainArr.get(r)) {
            peak = r;
        } else {
            peak = l;
        }
        l = 0;
        r = peak;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (mountainArr.get(mid) == target) {
                return mid;
            } else if (mountainArr.get(mid) < target){
                l = mid;
            } else {
                r = mid;
            }
        }
        if (mountainArr.get(l) == target) {
            return l;
        }
        if (mountainArr.get(r) == target) {
            return r;
        }
        l = peak + 1;
        r = n - 1;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (mountainArr.get(mid) == target) {
                return mid;
            } else if (mountainArr.get(mid) < target){
                r = mid;
            } else {
                l = mid;
            }
        }
        if (mountainArr.get(l) == target) {
            return l;
        }
        if (mountainArr.get(r) == target) {
            return r;
        }
        return -1;
    }
}

/*

Intuition
Binary find peak in the mountain.
852. Peak Index in a Mountain Array
Binary find the target in strict increasing array
Binary find the target in strict decreasing array


 */