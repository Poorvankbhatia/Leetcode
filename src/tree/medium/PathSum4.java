/*

If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:
The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary with the depth smaller than 5.
You need to return the sum of all paths from the root towards the leaves.

Example 1:
Input: [113, 215, 221]
Output: 12
Explanation:
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.
Example 2:
Input: [113, 221]
Output: 4
Explanation:
The tree that the list represents is:
    3
     \
      1

The path sum is (3 + 1) = 4.

 */
package tree.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 27/08/17.
 */
public class PathSum4 {

    Map<Integer,Integer> map = new HashMap<>();

    public int pathSum(int[] nums) {

        if(nums==null || nums.length==0) {
            return 0;
        }

        for (Integer num : nums) {
            map.put(num/10,num%10);
        }

        int sum = nums[0]%10;

        return util(sum,getLeftIndex(11),getRightIndex(11));


    }

    // At every level we pass the previous cumulative sum.
    private int util(int prevSum,int left,int right) {

        int leftSum = 0,rightSum=0;
        if (map.containsKey(left)) {
            leftSum = util(map.get(left)+prevSum,getLeftIndex(left),getRightIndex(left));
        }

        if (map.containsKey(right)) {
            rightSum =  util(map.get(right)+prevSum,getLeftIndex(right),getRightIndex(right));
        }

        if(leftSum==0 && rightSum==0) {
            return prevSum;
        } else {
            return leftSum+rightSum;
        }

    }

    private int getLeftIndex(int current) {
        int n = current%10;
        switch (n) {
            case 1: return current+10;
            case 2: return current+11;
            case 3: return current+12;
            case 4: return current+13;
            default:return -1;
        }
    }

    private int getRightIndex(int current) {
        int n = current%10;
        switch (n) {
            case 1: return current+11;
            case 2: return current+12;
            case 3: return current+13;
            case 4: return current+14;
            default:return -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{115,215,224,325,336,446,458};
        System.out.println(new PathSum4().pathSum(nums));
    }

}

/*
Store level+position combo in a map.
 */