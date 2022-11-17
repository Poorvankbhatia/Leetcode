/*

Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.
The guards have gone and will come back in H hours.

Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas,
and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead,
and won't eat any more bananas during this hour.

Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.

Return the minimum integer K such that she can eat all the bananas within H hours.



Example 1:

Input: piles = [3,6,7,11], H = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], H = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], H = 6
Output: 23


Note:

1 <= piles.length <= 10^4
piles.length <= H <= 10^9
1 <= piles[i] <= 10^9

 */
package binarysearch.medium;

/**
 * Created by poorvank.b on 22/07/18.
 */
public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int H) {
        int minRate=1;
        int maxRate=Integer.MIN_VALUE;
        for(int p : piles) {
            maxRate = Math.max(p,maxRate);
        }
        /*while(minRate<maxRate) {
            int mid = minRate+(maxRate-minRate)/2;
            int hoursReq = hoursRequired(mid,piles);
            if(hoursReq>H) { // if hoursReq are greater than rate is less.
                minRate = mid+1;
            } else {
                maxRate = mid;
            }
        }
        return minRate;*/



        while (maxRate-minRate>1) {
            int mid = minRate+(maxRate-minRate)/2;
            int currentHours = hoursRequired(mid,piles);
            // if hours with the current rate > required hours, we need to increase the speed (rate of eating.)
            if(currentHours>H) {
                minRate = mid+1;
            } else {
                // if hours <= required rate, decrease the speed.
                maxRate = mid;
            }
        }

        return hoursRequired(minRate,piles)<=H?minRate:maxRate;

    }

    private int hoursRequired(int banana, int[] piles) {
        int count=0;
        for(int p : piles) {
            if(p<=banana) {
                count++;
            } else {
                count+=(p%banana==0)?p/banana:(p/banana)+1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new KokoEatingBananas().minEatingSpeed(new int[]{1000000000},2)); // 14
    }

}
