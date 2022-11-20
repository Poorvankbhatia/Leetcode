/*

You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 One envelope can fit into another if and only if both the width and height of one envelope is greater than the
 width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

 */
package dyanamicprogramming.hard;

import java.util.Arrays;

/**
 * Created by poorvank on 10/12/16.
 */
public class RussianDollEnvelopes {

    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,(a, b)->a[0]!=b[0]?a[0]-b[0]:b[1]-a[1]);
        int n = envelopes.length;
        int[][] dp = new int[n][2];
        dp[0] = envelopes[0];
        int len=0;
        for(int i=1;i<n;i++) {
            int height = envelopes[i][1];
            int pos = search(dp,height,len);
            // if height at found pos>height replace with smaller height
            if(dp[pos][1]>height) {
                dp[pos] = envelopes[i];
            }
            // update pos.
            if(pos>len) {
                len = pos;
                dp[len] = envelopes[i];
            }
        }
        return len+1;
    }

    private int search(int[][] dp, int currentHeight,int len) {
        int start = 0;
        int end = len;
        if (dp[start][1] > currentHeight) {
            return start;
        }
        if (dp[end][1] < currentHeight) {
            return end + 1;
        }
        while (end - start > 1) {
            int mid = start + (end - start) / 2;
            // if mid found.
            if (dp[mid][1] == currentHeight) {
                return mid;
            } else if (dp[mid][1] < currentHeight) { // if mid<currentHeight move right.
                start = mid + 1;
            } else { // move left if a mid is of a higher height.
                end = mid;
            }
        }
        return dp[start][1] >= currentHeight ? start : end;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1,1},{1,1},{1,1}
        };
        System.out.println(new RussianDollEnvelopes().maxEnvelopes(a));
    }

}


/*

[[1,2],[2,3],[3,4],[3,5],[4,5],[5,5],[5,6],[6,7],[7,8]]

A variation of LIS

Sorting can also be done like : Arrays.sort(envelopes, (a, b) -> a[0] - b[0]);


Sort the array. Ascend on width and descend on height if width are same.
Find the longest increasing subsequence based on height.
Since the width is increasing, we only need to consider height.
[3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting otherwise it will be counted as an increasing number if the order is [3, 3], [3, 4]


 */