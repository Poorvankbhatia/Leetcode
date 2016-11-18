/*

Given a non-empty array of integers, return the third maximum number in this array.
If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.

 */
package arrays.easy;

/**
 * Created by poorvank on 18/11/16.
 */
public class ThirdMaxNo {

    public int thirdMax(int[] nums) {

        Integer first = Integer.MIN_VALUE;
        for (Integer e : nums) {
            if(e>first) {
                first = e;
            }
        }

        Integer second = null;
        for (Integer e : nums) {
            if(e<first) {
                second = (second==null?e:Math.max(e,second));
            }
        }

        if(second==null) {
            return first;
        }

        Integer third = null;
        for (Integer e:nums) {
            if(e<second) {
                third = (third==null?e:Math.max(e,third));
            }
        }

        if(third==null) {
            return first;
        }

        return third;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,3,1};
        System.out.print(new ThirdMaxNo().thirdMax(nums));
    }

}
