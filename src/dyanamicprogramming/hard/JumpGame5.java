/*
Given an array of integers arr and an integer d. In one step you can jump from index i to index:

i + x where: i + x < arr.length and 0 < x <= d.
i - x where: i - x >= 0 and 0 < x <= d.
In addition, you can only jump from index i to index j if arr[i] > arr[j] and arr[i] > arr[k] for all indices k between i and j (More formally min(i, j) < k < max(i, j)).

You can choose any index of the array and start jumping. Return the maximum number of indices you can visit.

Notice that you can not jump outside of the array at any time.


Input: arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
Output: 4
Explanation: You can start at index 10. You can jump 10 --> 8 --> 6 --> 7 as shown.
Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 > 9.
You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 > 9.
Similarly You cannot jump from index 3 to index 2 or index 1.
Example 2:

Input: arr = [3,3,3,3,3], d = 3
Output: 1
Explanation: You can start at any index. You always cannot jump to any index.
Example 3:

Input: arr = [7,6,5,4,3,2,1], d = 1
Output: 7
Explanation: Start at index 0. You can visit all the indicies.
Example 4:

Input: arr = [7,1,7,1,7,1], d = 2
Output: 2
Example 5:

Input: arr = [66], d = 1
Output: 1


Constraints:

1 <= arr.length <= 1000
1 <= arr[i] <= 10^5
1 <= d <= arr.length
 */
package dyanamicprogramming.hard;

import java.util.Arrays;

public class JumpGame5 {

    private int[] dp = new int[1000]; // Store minimum jump at every index
    public int maxJumps(int[] arr, int d) {
        int max = Integer.MIN_VALUE;
        Arrays.fill(dp,-1);
        for (int i=0;i<arr.length;i++) {
            int val = util(arr,d,i);
            max = Math.max(val,max);
        }
        return max+1; // max is the number of jumps, indices visited would be +1
    }

    private int util(int[] arr,int d,int index) {
        if(dp[index]!=-1) {
            return dp[index];
        }
        int max = 0;
        // Check left
        for(int i=index-1;i>=index-d;i--) {
            if(i>=0) {
                if(arr[i]<arr[index]) {
                    max = Math.max(max,util(arr,d,i)+1);
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        // check right
        for(int i=index+1;i<=index+d;i++) {
            if(i<=arr.length-1) {
                if(arr[i]<arr[index]) {
                    max = Math.max(max,util(arr,d,i)+1);
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        dp[index]=max;
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new JumpGame5().maxJumps(new int[]{7,1,7,1},2));
    }

}

/*

Complexity : O(nd)

O(n) : https://mp.weixin.qq.com/s/kEQ00_WLqDTG6tbsjQ2Xjw

 */
