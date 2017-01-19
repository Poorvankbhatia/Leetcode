/*

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold
additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.

 */
package arrays.easy;

/**
 * Created by poorvank on 01/12/16.
 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {

        if(n==0) {
            return;
        }

        int i=m-1,j=n-1,k=(m+n-1);

        while (i>=0 && j>=0) {
            if(nums1[i]>=nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        while (i>=0) {
            nums1[k] = nums1[i];
            k--;i--;
        }

        while (j>=0) {
            nums1[k] = nums2[j];
            k--;j--;
        }

    }

}
