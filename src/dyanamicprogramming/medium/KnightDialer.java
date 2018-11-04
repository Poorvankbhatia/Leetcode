/*

This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.
Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.

Example 1:

Input: 1
Output: 10
Example 2:

Input: 2
Output: 20
Example 3:

Input: 3
Output: 46


Note:

1 <= N <= 5000

 */
package dyanamicprogramming.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 04/11/18.
 */
public class KnightDialer {

    private Map<String,Long> posHopCountMap;

    private Map<Integer,Integer[]> neighbourMap;

    private int mod = (int) (Math.pow(10,9)+7);

    public int knightDialer(int N) {

        posHopCountMap = new HashMap<>();
        neighbourMap = new HashMap<>();

        neighbourMap.put(0,new Integer[]{4,6});
        neighbourMap.put(1,new Integer[]{6,8});
        neighbourMap.put(2,new Integer[]{7,9});
        neighbourMap.put(3,new Integer[]{4,8});
        neighbourMap.put(4,new Integer[]{3,9,0});
        neighbourMap.put(5,null);
        neighbourMap.put(6,new Integer[]{1,7,0});
        neighbourMap.put(7,new Integer[]{2,6});
        neighbourMap.put(8,new Integer[]{1,3});
        neighbourMap.put(9,new Integer[]{2,4});


        long sum = 0;
        for (int i=0;i<=9;i++) {
            sum=(sum+getDistinctCount(i,N-1))%mod;
        }

        return (int)sum;

    }

    private long getDistinctCount(int pos,int count) {
        if(count==0) {
            return 1;
        }
        String mapKey = pos+"_"+count;
        if(posHopCountMap.get(mapKey)!=null) {
            return (posHopCountMap.get(mapKey));
        }

        long c=0;
        if(neighbourMap.get(pos)!=null) {
            for (Integer ne : neighbourMap.get(pos)) {
                c=(c+getDistinctCount(ne,count-1))%mod;
            }
            posHopCountMap.put(mapKey,c);
        }

        return c;

    }

    public static void main(String[] args) {
        System.out.println(new KnightDialer().knightDialer(161));
    }


}
