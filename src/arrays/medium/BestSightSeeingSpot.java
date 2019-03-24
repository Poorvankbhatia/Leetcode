/*

Given an array A of positive integers, A[i] represents the value of the i-th sightseeing spot, and two sightseeing spots i and j have distance j - i between them.

The score of a pair (i < j) of sightseeing spots is (A[i] + A[j] + i - j) : the sum of the values of the sightseeing spots, minus the distance between them.

Return the maximum score of a pair of sightseeing spots.



Example 1:

Input: [8,1,5,2,6]
Output: 11
Explanation: i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11


Note:

2 <= A.length <= 50000
1 <= A[i] <= 1000

 */
package arrays.medium;

public class BestSightSeeingSpot {
    public int maxScoreSightseeingPair(int[] A) {
        int max = 0, ans = 0;           // use max to record max A[i] + i before j
        for(int j = 0; j < A.length; j ++)
        {
            int sum = A[j] - j + max;   // calculate the score
            if(sum > ans) ans = sum;
            sum = A[j] + j;             // update max if possible
            if(sum > max) max = sum;
        }
        return ans;
    }
}

/*

Count the current best score in all previous sightseeing spot.
Note that, as we go further, the score of previous spot decrement.

 */