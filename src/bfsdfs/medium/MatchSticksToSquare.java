/*

Remember the story of Little Match Girl? By now, you know exactly what matchsticks the little match girl has,
please find out a way you can make one square by using up all those matchsticks. You should not break any stick,
but you can link them up, and each matchstick must be used exactly one time.

Your input will be several matchsticks the girl has, represented with their stick length. Your output will either be
true or false, to represent whether you could make one square using all the matchsticks the little match girl has.

Example 1:
Input: [1,1,2,2,2]
Output: true

Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
Example 2:
Input: [3,3,3,3,4]
Output: false

Explanation: You cannot find a way to form a square with all the matchsticks.
Note:
The length sum of the given matchsticks is in the range of 0 to 10^9.
The length of the given matchstick array will not exceed 15.

 */
package bfsdfs.medium;

/**
 * Created by poorvank.b on 31/08/18.
 */
public class MatchSticksToSquare {

    public boolean makesquare(int[] nums) {

        int sum=0;
        for (int n : nums) {
            sum+=n;
        }
        int sides = 4;

        return (sum%sides==0) && canPartition(sum/sides,new boolean[nums.length],nums,sides,0,0);
    }

    private boolean canPartition(int target,boolean[] visited,int[] nums,int sides,int startIndex,int currentSum) {

        if(sides==0) {
            return true;
        }
        if(currentSum==target) {
            return canPartition(target,visited,nums,sides-1,0,0);
        }
        for (int i=startIndex;i<nums.length;i++) {
            if(!visited[i]) {
                visited[i]=true;
                if(canPartition(target,visited,nums,sides,i+1,currentSum+nums[i])) {
                    return true;
                }
                visited[i]=false;
            }
        }
        return false;

    }

}

/*

Same as K Subset problem - complexity - O(4^N) because we have a total of N sticks and for each one of those matchsticks,
we have 4 different possibilities for the subsets they might belong to or the side of the square they might be a part of.

 */