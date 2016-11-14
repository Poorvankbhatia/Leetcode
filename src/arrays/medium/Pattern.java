/*

Given a sequence of n integers a1, a2, ..., an, a 132 pattern is a subsequence ai, aj, ak such
that i < j < k and ai < ak < aj. Design an algorithm that takes a list of n numbers as input and checks
whether there is a 132 pattern in the list.

Note: n will be less than 15,000.

Example 1:
Input: [1, 2, 3, 4]

Output: False

Explanation: There is no 132 pattern in the sequence.
Example 2:
Input: [3, 1, 4, 2]

Output: True

Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
Example 3:
Input: [-1, 3, 2, 0]

Output: True

Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].

 */

package arrays.medium;

/**
 * Created by poorvank on 13/11/16.
 */
public class Pattern {

    public boolean find132pattern(int[] nums) {


        int len = nums.length;
        if(len==0) {
            return false;
        }
        int[] greatestElement = new int[len];
        greatestElement[len-1] = nums[len-1];

        for (int i=0;i<len;i++) {
            int max = Integer.MIN_VALUE;
            for (int j=i+1;j<len;j++) {
                if(nums[j]<nums[i] && nums[j]>max) {
                    max = nums[j];
                }
            }
            greatestElement[i] = max;
        }


        int i=0;
        while (i<len-2) {
            int j = i+1;
            while (j<len-1) {
                if(nums[j]>nums[i] && greatestElement[j]>nums[i] && greatestElement[j]!=Integer.MIN_VALUE) {
                    return true;
                }
                j++;
            }
            i++;
        }

        return false;


    }

    public static void main(String[] args) {
        int[] a = new int[]{0,1,1,1,0,1,0,0,1,0,1,0,0,1,0,0,1,0,1,0,1,1,1,0,1,1,1,1,1,0,0,1,1,0,1,1,1,0,1,0,1,1,1,0,1,0,0,0,1,0,1,0,1,0,0,1,0,0,1,1,1,0,1,0,0,0,1,1,1,1,0,1,1,1,1,1,0,0,1,1,1,1,1,0,0,0,1,0,1,0,1,1,0,1,0,1,1,1,0,0,0,1,1,1,0,0,0,1,0,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,1,1,1,1,0,0,1,0,1,1,1,0,1,1,0,0,1,0,1,1,0,1,0,1,1,1,1,1,0,1,0,0,1,0,1,1,0,1,0,0,1,1,1,1,1,1,1,1,1,1,0,0,1,0,1,0,0,1,0,1,0,0,1,0,1,0,1,0,1,0,0,0,0,1,1,0,1,1,1,1,0,1,0,1,1,1,0,0,1,0,0,1,0,0,1,0,1,1,0,0,1,1,1,1,0,1,0,0,1,0,0,0,0,1,0,1,1,1,1,0,0,1,0,0,0,0,1,0,1,0,1,1,0,1,0,0,0,1,1,0,0,1,1,1,1,0,0,1,1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,0,0,0,0,1,1,1,1,0,1,0,0,0,0,1,1,1,1,1,0,1,1,0,0,1,1,1,0,1,1,0,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,1,0,1,1,0,0,1,1,0,0,0,1,0,0,0,1,1,0,0,1,1,1,1,1,1,0,1,0,0,1,0,0,0,0,1,0,1,0,0,0,1,1,1,1,1,0,0,0,1,1,1,0,1,0,0,1,0,0,0,0,1,1,1,1,0,1,0,1,1,0,1,1,1,0,0,1,0,0,1,0,0,1,1,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,1,1,0,1,1,0,0,0,1,1,0,1,0,0,0,1,0,0,1,0,0,0,1,0,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,0,1,0,1,1,0,1,0,1,1,0,0,0,1,1,1,1,1,1,0,1,0,1,0,0,0,1,0,1,1,1,0,0,1,1,0,0,0,0,0,1,0,1,1,0,0,0,0,1,1,0,1,0,1,1,0,1,1,1,0};
        //int[] a = new int[]{-1,3,2,0};
        //int[] a = new int[]{-1,2,3,4};
        System.out.println(new Pattern().find132pattern(a));
    }

}
