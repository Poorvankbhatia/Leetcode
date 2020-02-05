/*

In a country popular for train travel, you have planned some train travelling one year in advance.
The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

Train tickets are sold in 3 different ways:

a 1-day pass is sold for costs[0] dollars;
a 7-day pass is sold for costs[1] dollars;
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2,
then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

Return the minimum number of dollars you need to travel every day in the given list of days.



Example 1:

Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation:
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total you spent $11 and covered all the days of your travel.
Example 2:

Input: days = [1,2,3,4,5,6,7,8,9,10,30,31], costs = [2,7,15]
Output: 17
Explanation:
For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 30-day pass for costs[2] = $15 which covered days 1, 2, ..., 30.
On day 31, you bought a 1-day pass for costs[0] = $2 which covered day 31.
In total you spent $17 and covered all the days of your travel.


Note:

1 <= days.length <= 365
1 <= days[i] <= 365
days is in strictly increasing order.
costs.length == 3
1 <= costs[i] <= 1000

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank.b on 27/01/19.
 */
public class MinimumCostForTickets {

    public int mincostTickets(int[] days, int[] costs) {
        boolean[] dayIncluded = new boolean[366];
        for (int day : days) {
            dayIncluded[day] = true;
        }
        int[] minCost = new int[366];
        minCost[0] = 0;
        for (int day = 1; day <= 365; ++day) {
            if (!dayIncluded[day]) {
                minCost[day] = minCost[day-1];
                continue;
            }
            int min;
            min = minCost[day-1] + costs[0];
            min = Math.min(min, minCost[Math.max(0, day-7)] + costs[1]);
            min = Math.min(min, minCost[Math.max(0, day-30)] + costs[2]);
            minCost[day] = min;
        }

        return minCost[365];

    }

}

/*

Let minCost(i) denote the minimum cost that will be payed for all trips on days 1 to day 365.
The desired answer is then minCost(365).

Calculation minCost(i):

If no trip on day i, then minCost(i) = minCost(i-1).
 minCost(i)=0 for all i ≤ 0.
Otherwise:
If a 1-day pass on day i. In this case,  minCost(i) =  minCost(i) + costs[0].
If a 7-day pass ending on day i. then : In this case, minCost(i) = min(minCost(i − 7), MC(i − 6), …, minCost(i − 1)) + costs[1].
But since since minCost is increasing (adding a day never reduces the minCost) hence:
 minCost(i) = minCost(i − 7) + 7

Same case for 30-day pass also.

 */