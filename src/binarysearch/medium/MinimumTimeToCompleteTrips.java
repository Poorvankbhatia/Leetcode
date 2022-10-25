/*
You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.

Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the
current trip. Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.

You are also given an integer totalTrips, which denotes the number of trips all buses should make in total.
Return the minimum time required for all buses to complete at least totalTrips trips.



Example 1:

Input: time = [1,2,3], totalTrips = 5
Output: 3
Explanation:
- At time t = 1, the number of trips completed by each bus are [1,0,0].
  The total number of trips completed is 1 + 0 + 0 = 1.
- At time t = 2, the number of trips completed by each bus are [2,1,0].
  The total number of trips completed is 2 + 1 + 0 = 3.
- At time t = 3, the number of trips completed by each bus are [3,1,1].
  The total number of trips completed is 3 + 1 + 1 = 5.
So the minimum time needed for all buses to complete at least 5 trips is 3.
Example 2:

Input: time = [2], totalTrips = 1
Output: 2
Explanation:
There is only one bus, and it will complete its first trip at t = 2.
So the minimum time needed to complete 1 trip is 2.


Constraints:

1 <= time.length <= 105
1 <= time[i], totalTrips <= 107
 */
package binarysearch.medium;

import java.util.Arrays;

public class MinimumTimeToCompleteTrips {

    public long minimumTime(int[] time, int totalTrips) {
        long min = (Arrays.stream(time).min()).getAsInt();
        long max = Long.MAX_VALUE;
        while (min < max) {
            long mid = min+(max-min)/2;
            if(canComplete(time, mid, totalTrips)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    private boolean canComplete(int[] time, long mid, int reqTrips) {
        long ans = 0;
        for (int j : time) {
            ans += (mid / j);
            if(ans >= reqTrips) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumTimeToCompleteTrips().minimumTime(new int[]{39,82,69,37,78,14,93,36,66,61,13,58,57,
                12,70,14,67,75,91,1,34,68,73,50,13,40,81,21,79,12,35,18,71,43,5,50,37,16,15,6,61,7,87,43,27,62,95,45,82,100,15,74,33,95,38,88,91,47,22,82,51,19,10,24,
                87,38,5,91,10,36,56,86,48,92,10,26,63,2,50,88,9,83,20,42,59,55,8,15,48,25},4187));
    }

}

/*
Given a time compute the total trips that are possible.
Use Binary Search.
 */