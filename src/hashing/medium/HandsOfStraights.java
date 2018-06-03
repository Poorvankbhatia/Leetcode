/*

Alice has a hand of cards, given as an array of integers.

Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.

Return true if and only if she can.



Example 1:

Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
Example 2:

Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.


Note:

1 <= hand.length <= 10000
0 <= hand[i] <= 10^9
1 <= W <= hand.length

 */
package hashing.medium;

import java.util.TreeMap;

/**
 * Created by poorvank.b on 03/06/18.
 */
public class HandsOfStraights {

    public boolean isNStraightHand(int[] hand, int W) {

        TreeMap<Integer,Integer> map = new TreeMap<>();
        for (int h : hand) {
            map.put(h,map.getOrDefault(h,0)+1);
        }


        while (!map.isEmpty()) {

            int key = map.firstKey();
            int count = W;
            while (count!=0) {
                if(map.get(key)==1) {
                    map.remove(key);
                } else {
                    map.put(key,map.get(key)-1);
                }
                if(count>1 && !map.containsKey(key+1)) {
                    return false;
                }
                key = key+1;
                count--;
            }

        }

        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,2000,2001,5000,5001};
        int W = 2;
        System.out.println(new HandsOfStraights().isNStraightHand(arr,W));
    }

}
