/*

Minimum Moves to Equal Array Elements My SubmissionsBack To Contest
User Accepted: 334
User Tried: 763
Total Accepted: 357
Total Submissions: 905
Difficulty: Easy
Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal,
where a move is incrementing n - 1 elements by 1.

Example:

Input:
[1,2,3]

Output:
3

Explanation:
Only three moves are needed (remember each move increments two elements):

[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

 */
package arrays.easy;

/**
 * Created by poorvank.b on 06/11/16.
 */
public class MimimumMoves {

    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (Integer element : nums) {
            if (min > element) {
                min = element;
            }
        }
        int result = 0;
        for (Integer element : nums) {
            result += element - min;
        }
        return result;
    }

}
