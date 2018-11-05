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
        if(envelopes==null || envelopes.length==0) {
            return 0;
        }

        Arrays.sort(envelopes,(a,b)->(a[0]!=b[0])?a[0]-b[0]:b[1]-a[1]);

        int n = envelopes.length;

        int[][] dp = new int[n][2];

        dp[0] = envelopes[0];
        int len=0;

        for(int i=1;i<n;i++) {
            int pos = getpos(dp,len,envelopes[i]);
            System.out.println(pos+" "+"("+envelopes[i][0]+","+envelopes[i][1]+")");
            if(dp[pos][1]>envelopes[i][1]) {
                dp[pos][0] = envelopes[i][0];
                dp[pos][1] = envelopes[i][1];
            }
            if(pos>len) {
                len=pos;
                dp[len][0]=envelopes[i][0];
                dp[len][1]=envelopes[i][1];
            }
        }

        return len+1;

    }

    private int getpos(int[][] dp,int len,int[] envelope) {

        int start=0;
        int end = len;

        System.out.println("len "  + len);

        if(dp[start][1]>envelope[1]) {
            return start;
        }

        if(dp[end][1]<envelope[1]) {
            return end+1;
        }

        while(start<end) {
            int mid =start+(end-start)/2;
            if(dp[mid][1]==envelope[1]) {
                return mid;
            }
            if(dp[mid][1]>envelope[1]) {
                end=mid;
            } else {
                start=mid+1;
            }
        }

        return start;

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

 */