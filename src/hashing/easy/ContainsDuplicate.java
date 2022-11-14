/*

Given an array of integers, find if the array contains any duplicates.
Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

 */
package hashing.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank.b on 02/12/16.
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {

        if(nums.length==0 || nums.length==1) {
            return false;
        }

        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if(!set.add(num)) return false;
        }

        return true;

    }

    public static void main(String[] args) {
        int[] arr = new int[300000];
        for (int i=0;i<300000;i++) {
            arr[i] = i;
        }
        System.out.println(new ContainsDuplicate().containsDuplicate(arr));
    }

}
