/*
There are several cards arranged in a row, and each card has an associated number of points The points are given in the integer array cardPoints.

In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.

Your score is the sum of the points of the cards you have taken.

Given the integer array cardPoints and the integer k, return the maximum score you can obtain.



Example 1:

Input: cardPoints = [1,2,3,4,5,6,1], k = 3
Output: 12
Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
Example 2:

Input: cardPoints = [2,2,2], k = 2
Output: 4
Explanation: Regardless of which two cards you take, your score will always be 4.
Example 3:

Input: cardPoints = [9,7,7,9,7,7,9], k = 7
Output: 55
Explanation: You have to take all the cards. Your score is the sum of points of all cards.
Example 4:

Input: cardPoints = [1,1000,1], k = 1
Output: 1
Explanation: You cannot take the card in the middle. Your best score is 1.
Example 5:

Input: cardPoints = [1,79,80,1,1,1,200,1], k = 3
Output: 202


Constraints:

1 <= cardPoints.length <= 10^5
1 <= cardPoints[i] <= 10^4
1 <= k <= cardPoints.length
 */
package arrays.medium;

public class MaximumPointsFromCards {
    public int maxScore(int[] cardPoints, int k) {
       int sum = 0;
       for (int i=0;i<k;i++) {
           sum+=cardPoints[i];
       }
       int n = cardPoints.length;
       int ans = sum;
       int end = 0;
       while (end<k){
           sum-=cardPoints[k-1-end];
           sum+=cardPoints[n-1-end];
           ans = Math.max(ans,sum);
           end++;
       }
       return ans;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumPointsFromCards().maxScore(new int[]{1,79,80,1,1,1,200,1},3));
    }

}

/*

G I

DP Solution:

if we want to take k cards, we could :

take k cards from left.
take k cards from right.
take n(n >=1) cards from left, and take (k-n) cards from right.
look at the above picture,
let i be the cards number took from left and let k =4.
if i = 0, then total_cardpoints(i) = total points of the last k cards.(in this case 1+ 7+10+11 ).
if i = 1, then total_cardpoints(1)= total_cardpoints (0) + point[0] - point[5] .
if i = 2, then total_cardpoints (2) = total_cardpoints (1) + point[1] - point[6] .


 public int maxScore(int[] cardPoints, int k) {
        int[] dp = new int[k + 1];
        //   this dp array stores the total points when taking i cards from left.
        //   since we could take 0 - k cards from left ,the length of the dp array would be k+1.

        for (int i = cardPoints.length - 1; i >= cardPoints.length - k; i--) {
            dp[0] += cardPoints[i];
        }
        int max_points = dp[0];

        for (int i = 1; i < k + 1; i++) {
            dp[i] = dp[i - 1] + cardPoints[i - 1] - cardPoints[cardPoints.length - k + i - 1];
            max_points = Math.max(max_points, dp[i]);
        }

        return max_points;
    }

 */