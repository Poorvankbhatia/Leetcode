/*

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

 */
package dyanamicprogramming.hard;

/**
 * Created by poorvank on 02/01/17.
 */
public class JumpGame2 {

    public int jump(int[] nums) {

        int currentEnd=0,currentFarthest=0,jumps=0;

        for (int i=0;i<nums.length-1;i++) {

            currentFarthest = Math.max(currentFarthest,i+nums[i]);
            if(i==currentEnd) {
                jumps++;
                currentEnd=currentFarthest;
            }

        }

        return jumps;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};

        System.out.println(new JumpGame2().jump(nums));
    }

}

/**
 * The main idea is based on greedy. Let's say the range of the current jump is [curBegin, curEnd],
 * curFarthest is the farthest point that all points in [curBegin, curEnd] can reach.
 * Once the current point reaches curEnd, then trigger another jump, and set the new curEnd with curFarthest, then keep the above steps.
 *
 */