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
        int l = 1, r = Integer.MIN_VALUE;
        for(int val : piles)
            r = Math.max(val, r);
        while(l != r){
            int mid = (l)+(r-l)/2;
            if(isPossible(mid, H, piles))
                r = mid;
            else
                l = mid+1;
        }
        return l;
    }

    private boolean isPossible(int mid, int H, int[] piles){
        for(int val : piles){
            H -= (int)Math.ceil(val * (1.0 / mid));
        }
        return H >= 0;
    }

}
