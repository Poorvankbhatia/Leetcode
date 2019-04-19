/*

In a list of songs, the i-th song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration in seconds is divisible by 60.
Formally, we want the number of indices i < j with (time[i] + time[j]) % 60 == 0.



Example 1:

Input: [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.


Note:

1 <= time.length <= 60000
1 <= time[i] <= 500

 */
package arrays.easy;

import java.util.HashMap;
import java.util.Map;

public class SongPairsWithDuration {

    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = 0;
        for (int t : time) {
            int d = (60 - t % 60) % 60;
            if (map.containsKey(d)) { n += map.get(d); }
            map.put(t % 60, map.getOrDefault(t % 60, 0) + 1);
        }
        return n;
    }



    public static void main(String[] args) {
        System.out.println(new SongPairsWithDuration().numPairsDivisibleBy60(new int[]{60,60,60}));
    }

}

/*

Calculate the time % 60 then it will be exactly same as two sum problem.
t % 60 gets the remainder 0 ~ 59.
We count the occurrence of each remainders in a array/hashmap c.

we want to know that, for each t, how many x satisfy (t + x) % 60 = 0.

t % 60 + x % 60 = 60 for the most cases.

It has to be noticed that, if t % 60 = 0, x % 60 = 0 instead of 60.

60 - t % 60 will get a number in range 1 ~ 60.

(60 - t % 60) % 60 can get number in range 0 ~ 59

 */