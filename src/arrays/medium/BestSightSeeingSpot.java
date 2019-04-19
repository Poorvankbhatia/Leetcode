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
        int i=0;
        int max = A[i] + i;
        for(int j=1;j<A.length;j++){
            int curr = A[i] + A[j] + i - j;
            max = curr > max ? curr : max;

            // update the value of i to the one that maximizes
            if(A[i] + i < A[j] + j){
                i=j;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{8,1,5,2,6};
        new BestSightSeeingSpot().maxScoreSightseeingPair(arr);
    }
}

/*

We want to maximize A[i] + A[j] + i - j where j > i, meaning i is to the left and j is to the right

One way to think about this is that we somehow make sure that we add the best value of A[i] + i to A[j] - j
as we go from left to right. So, at any point, while going towards right end, we add the best value available to the left.

Other way to think is to make sure we add the best value of A[j]-j to A[i] + i as we go from right to left.
 So, at any point, while going towards left end, we add the best value available to the right.
public int maxScoreSightseeingPair(int[] A) {
        int N = A.length;
		int j=N-1;
        int max = A[j] - j;
        for(int i=N-2;i>=0; i--){
            int curr = A[i] + A[j] + i - j;
            max = curr > max ? curr : max;

            // update the value of j to the one that maximizes
            if(A[j] - j < A[i] - i){
                j=i;
            }
        }
        return max;
    }
 */