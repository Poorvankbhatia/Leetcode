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

        if(nums.length==1) {
            return 0;
        }
        int start=0,end=0;
        int jump=0;
        int i=0;
        while(i<nums.length) {
            if(start>end) {
                break;
            }
            int far=0;
            for(int j=start;j<=end;j++) {
                far = Math.max(far,nums[j]+j);
                if(far>=nums.length-1) {
                    return jump+1;
                }
            }
            start=end+1;
            end=far;
            jump++;
            i++;
        }

        return -1;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,1,4};

        System.out.println(new JumpGame2().jump(nums));
    }

}

/*

Try to change this problem to a BFS problem, where nodes in level i are all the nodes that can be reached in i-1th jump.
for example. 2 3 1 1 4 , is
2||
3 1||
1 4 ||

clearly, the minimum jump of 4 is 2 since 4 is in level 3

 */