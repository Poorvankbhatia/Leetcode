/*

Students are asked to stand in non-decreasing order of heights for an annual photo.

Return the minimum number of students not standing in the right positions.
(This is the number of students that must move in order for all students to be standing in non-decreasing order of height.)



Example 1:

Input: [1,1,4,2,1,3]
Output: 3
Explanation:
Students with heights 4, 3 and the last 1 are not standing in the right positions.


Note:

1 <= heights.length <= 100
1 <= heights[i] <= 100

 */
package arrays.easy;

import java.util.Arrays;

public class HeighrChecker {
    public int heightChecker(int[] heights) {
        int[] h = new int[heights.length];
        int i=0;
        for(int k : heights) {
            h[i++] = k;
        }
        Arrays.sort(h);
        int ans=0;
        for(int x=0;x<h.length;x++) {
            if(h[x]!=heights[x]) {
                ans++;
            }
        }
        return ans;
    }
}

/*

If you consider the input
[1,2,1,2,1,1,1,2,1]

LeetCode's solution (at least in today's contest) was 4 moves for the above problem.
And it's coming from the sort and compare solution strategy or something similar.
But the minimum number of student moves for them to be in order of non-decreasing height is 3.
You move each 2 to the end of the array one by one and you can sort the array in 3 student moves.
I think the definition should just be how many students are out of order.

 */