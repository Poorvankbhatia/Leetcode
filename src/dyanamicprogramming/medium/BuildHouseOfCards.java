/*
You are given an integer n representing the number of playing cards you have. A house of cards meets the following conditions:

A house of cards consists of one or more rows of triangles and horizontal cards.
Triangles are created by leaning two cards against each other.
One card must be placed horizontally between all adjacent triangles in a row.
Any triangle on a row higher than the first must be placed on a horizontal card from the previous row.
Each triangle is placed in the leftmost available spot in the row.
Return the number of distinct house of cards you can build using all n cards.
Two houses of cards are considered distinct if there exists a row where the two houses contain a different number of cards.

Input: n = 16
Output: 2
Explanation: The two valid houses of cards are shown.
The third house of cards in the diagram is not valid because the rightmost triangle on the top row is not placed on top of a horizontal card.


Input: n = 4
Output: 0
Explanation: The three houses of cards in the diagram are not valid.
The first house of cards needs a horizontal card placed between the two triangles.
The second house of cards uses 5 cards.
The third house of cards uses 2 cards.


 */
package dyanamicprogramming.medium;

import java.util.Arrays;

public class BuildHouseOfCards {

    public int houseOfCards(int n) {

        int[][] dp = new int[n+1][n+2];
        for (int[] d : dp) Arrays.fill(d,-1);
        return find(n,n+1,dp);
    }

    private int find(int n,int lastRow,int[][] dp) {
        if(n==0 || n==2) {
            return 1;
        }
        if (dp[n][lastRow]!= -1) {
            return dp[n][lastRow];
        }
        int count = 0;
        for (int i=5;i<=n&& i<lastRow;i+=3) count+=find(n-i,i,dp);
        dp[n][lastRow]=count;
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new BuildHouseOfCards().houseOfCards(3));
    }

}


/*
The basic idea is that you can have a valid row if the number of cards is 2 or a
multiple of 3 starting from 5 (5, 8, 11, 14, ...).
The recursive approach is using two parameters:
    the number of remaining cards and the number of cards used for the last row;
            since the first call doesn't have a lastRow yet,
            and since the lastRow must be greater that the current row (otherwise the house will collapse),
 the first call has lastRow equal to n + 1. Memoization is straightforward given the two parameters.


 */