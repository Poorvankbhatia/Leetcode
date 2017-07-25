/*

The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated
to another number in the set, which results in repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then
find the number that is missing. Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Note:
The given array size will in the range [2, 10000].
The given array's numbers won't have any order.

 */
package hashing.easy;

import java.util.HashMap;

/**
 * Created by poorvank on 25/07/17.
 */
public class SubsetMismatch {

    public int[] findErrorNums(int[] nums) {

        if(nums==null || nums.length==0) {
            return new int[]{};
        }

        HashMap<Integer,Integer> map = new HashMap<>();

        int n = nums.length;
        int[] result = new int[2];

        for (int num : nums) {
            if (map.containsKey(num)) {
                result[0] = num;
            } else {
                map.put(num, 1);
            }
        }

        for (int i=1;i<=n;i++) {
            if(!map.containsKey(i)) {
                result[1] = i;
            }
        }

        return result;

    }

}

/*

Method 2:

 if(nums==null || nums.length==0) {
            return new int[]{};
        }

        int n = nums.length;

        int requiredSum = (n)*(n+1)/2;

        int sumPresent = 0;

        int repeatedElement = 0;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if(set.contains(num)) {
                repeatedElement = num;
            }
            set.add(num);
            sumPresent += num;
        }

        int extra = Math.abs(requiredSum-sumPresent);

        int deletedElement = requiredSum-sumPresent>0?extra+repeatedElement:repeatedElement-extra;

        return new int[]{repeatedElement,deletedElement};

 */