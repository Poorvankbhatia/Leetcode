/*
You are given a 0-indexed integer array candies. Each element in the array denotes a pile of candies of size candies[i].
 You can divide each pile into any number of sub piles, but you cannot merge two piles together.

You are also given an integer k. You should allocate piles of candies to k children such that each child gets the
same number of candies. Each child can take at most one pile of candies and some piles of candies may go unused.

Return the maximum number of candies each child can get.



Example 1:

Input: candies = [5,8,6], k = 3
Output: 5
Explanation: We can divide candies[1] into 2 piles of size 5 and 3, and candies[2] into 2 piles of size 5 and 1.
We now have five piles of candies of sizes 5, 5, 3, 5, and 1. We can allocate the 3 piles of size 5 to 3 children.
It can be proven that each child cannot receive more than 5 candies.
Example 2:

Input: candies = [2,5], k = 11
Output: 0
Explanation: There are 11 children but only 7 candies in total, so it is impossible to ensure each child receives at
least one candy. Thus, each child gets no candy and the answer is 0.


Constraints:

1 <= candies.length <= 105
1 <= candies[i] <= 107
1 <= k <= 1012
 */
package binarysearch.medium;

public class MaxCandies {

    public int maximumCandies(int[] candies, long k) {
        int hi =0;
        long sum = 0;
        for (int c : candies) {
            hi = Math.max(hi, c);
            sum += c;
        }
        if(sum<k) {
            return 0;
        }
        // max candies will be max of(the maximum of all candies or sum/k)
        // because in case k is very close to the sum but not> sum, then iterating till
        // max does not make sense.
        hi = (int) Math.min(hi,(sum/k));
        int lo = 0;
        int ans = 0;
        while (hi-lo>1) {
            int mid = lo + (hi - lo)/2;
            if(possibleDivide(mid,candies,k)) {
                lo = mid;
            } else {
                hi = mid-1;
            }
        }
        return possibleDivide(hi,candies,k)?hi:lo;

    }

    private boolean possibleDivide(int count, int[] candies, long k) {
        if(count==0) return true;
        long d = 0;
        for(int c : candies) {
            d += c/count;
            if(d>=k) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new MaxCandies().maximumCandies(new int[]{5,8,6},3));
    }

}

/*

Read about upper and lower bounds:
https://medium.com/swlh/binary-search-find-upper-and-lower-bound-3f07867d81fb
This is asking for an upper bound BS:

https://www.youtube.com/watch?v=egRrgj8JOdY&ab_channel=Luv

 */
