/*
You are given an integer array bloomDay, an integer m and an integer k.

You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.

The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and then can be used in exactly one bouquet.

Return the minimum number of days you need to wait to be able to make m bouquets from the garden.
If it is impossible to make m bouquets return -1.



Example 1:

Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
Output: 3
Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower did not
bloom in the garden.
We need 3 bouquets each should contain 1 flower.
After day 1: [x, _, _, _, _]   // we can only make one bouquet.
After day 2: [x, _, _, _, x]   // we can only make two bouquets.
After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
Example 2:

Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
Output: -1
Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers
so it is impossible to get the needed bouquets and we return -1.
Example 3:

Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
Output: 12
Explanation: We need 2 bouquets each should have 3 flowers.
Here is the garden after the 7 and 12 days:
After day 7: [x, x, x, x, _, x, x]
We can make one bouquet of the first three flowers that bloomed. We cannot make another bouquet from the last three
flowers that bloomed because they are not adjacent.
After day 12: [x, x, x, x, x, x, x]
It is obvious that we can make two bouquets in different ways.


Constraints:

bloomDay.length == n
1 <= n <= 105
1 <= bloomDay[i] <= 109
1 <= m <= 106
1 <= k <= n
 */
package binarysearch.medium;

public class MinimumNumberOfBloomDays {

    public int minDays(int[] bloomDay, int m, int k) {
        long val = ((long) m *k);
        if(val > bloomDay.length) {
            return -1;
        }
        int lo=Integer.MAX_VALUE;int hi=Integer.MIN_VALUE;
        for (int x: bloomDay) {
            lo = Math.min(lo,x);
            hi = Math.max(hi,x);
        }
        while(lo<hi) {
            int mid = lo + (hi - lo) /2 ;
            if(readyBouquets(bloomDay, mid, m, k)) {
                hi = mid;
            } else {
                lo = mid+1;
            }
        }
        return lo;
    }

    private boolean readyBouquets(int[] bloomDay, int days, int m, int k) {
        int[] temp = new int[bloomDay.length];
        for (int i=0;i<temp.length;i++) {
            if(bloomDay[i] <= days) {
                temp[i]+=1;
            }
        }
        int consecutive = k;
        for (int j : temp) {
            if (j == 1) {
                consecutive--;
                if (consecutive == 0) {
                    m--;
                    consecutive = k;
                }
            } else {
                consecutive = k;
            }
        }
        return m<=0;
    }

    public static void main(String[] args) {
        System.out.println((long) 89945*32127);
        System.out.println(new MinimumNumberOfBloomDays().minDays(new int[] {1,10,2,9,3,8,4,7,5,6},4,2));
    }

}

/*
Check if enough bouquets at day can be made using adjacent flowers.
if yes try for less else increase the days in a bs format.
 */
