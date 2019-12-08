/*

Given two arrays, write a function to compute their intersection.

Example:
Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

Note:
Each element in the result must be unique.
The result can be in any order.

 */
package hashing.medium;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by poorvank on 11/10/16.
 */
public class IntersectionArray {
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1==null || nums2==null || nums1.length==0 || nums2.length==0) {
            return new int[]{};
        }
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        for (int n : nums1) {
            set.add(n);
        }

        for (int i : nums2) {
            if (set.contains(i)) {
                result.add(i);
            }
        }

        int[] resultArr = new int[result.size()];
        int j=0;
        for (Integer element : result) {
            resultArr[j] = element;
            j++;
        }
        return resultArr;
    }

    public static void main(String[] args) {

        int[] a = new int[]{4,7,9,7,6,7};
        int[] b = new int[]{5,0,0,6,1,6,2,2,4};

        System.out.println(Arrays.toString(new IntersectionArray().intersection(a,b)));

    }

}
