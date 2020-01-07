/*

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

 */

package dyanamicprogramming.medium;

/**
 * Created by poorvank on 21/11/16.
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        int maximumReach=0;
        for(int i=0;i<nums.length;i++) {
            if(i>maximumReach) {
                return false;
            }
            maximumReach = Math.max(maximumReach,nums[i]+i);
            if(maximumReach>=nums.length-1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
                ,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
                ,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
                1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
                ,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
                ,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        System.out.print(new JumpGame().canJump(nums));
    }
}


/*

The basic idea is this: at each step, we keep track of the furthest reachable index. The nature of the problem
(eg. maximal jumps where you can hit a
range of targets instead of singular jumps where you can only hit one target) is that for an index to
be reachable, each of the previous indices have to be reachable.

Hence, it suffices that we iterate over each index, and If we ever encounter an index that is not reachable, we abort and
return false. By the end, we will have iterated to the last index. If the loop finishes, then the last index is reachable.

 */