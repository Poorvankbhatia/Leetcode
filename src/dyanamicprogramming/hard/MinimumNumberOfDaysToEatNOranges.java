/*
There are n oranges in the kitchen and you decided to eat some of these oranges every day as follows:

Eat one orange.
If the number of remaining oranges n is divisible by 2 then you can eat n / 2 oranges.
If the number of remaining oranges n is divisible by 3 then you can eat 2 * (n / 3) oranges.
You can only choose one of the actions per day.

Given the integer n, return the minimum number of days to eat n oranges.



Example 1:

Input: n = 10
Output: 4
Explanation: You have 10 oranges.
Day 1: Eat 1 orange,  10 - 1 = 9.
Day 2: Eat 6 oranges, 9 - 2*(9/3) = 9 - 6 = 3. (Since 9 is divisible by 3)
Day 3: Eat 2 oranges, 3 - 2*(3/3) = 3 - 2 = 1.
Day 4: Eat the last orange  1 - 1  = 0.
You need at least 4 days to eat the 10 oranges.
Example 2:

Input: n = 6
Output: 3
Explanation: You have 6 oranges.
Day 1: Eat 3 oranges, 6 - 6/2 = 6 - 3 = 3. (Since 6 is divisible by 2).
Day 2: Eat 2 oranges, 3 - 2*(3/3) = 3 - 2 = 1. (Since 3 is divisible by 3)
Day 3: Eat the last orange  1 - 1  = 0.
You need at least 3 days to eat the 6 oranges.


Constraints:

1 <= n <= 2 * 109
 */
package dyanamicprogramming.hard;

import java.util.HashMap;
import java.util.Map;

public class MinimumNumberOfDaysToEatNOranges {


    public int minDays(int n) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        map.put(1,1);
        return minDaysUtil(n,map);
    }

    private int minDaysUtil(int n, Map<Integer,Integer> result) {
        if(result.containsKey(n)) {
            return result.get(n);
        }
        // remaining would be n/2
        int mod2 = n%2+minDaysUtil(n/2,result);
        // remaining would be n/3
        int mod3 = n%3+minDaysUtil(n/3,result);
        int ans = Math.min(mod2,mod3)+1;
        result.put(n,ans);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new MinimumNumberOfDaysToEatNOranges().minDays(3521));
    }


}

/*

BFS:

class Solution {
    public int minDays(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        int res = 0;
        Set<Integer> set = new HashSet<>();
        while(!q.isEmpty()){
            res++;
            int size = q.size();
            for(int i = 0; i < size; i++){
                int cur = q.poll();
                if(cur == 0){
                    return res - 1;
                }
                if(set.contains(cur)){
                    continue;
                }
                else{
                    set.add(cur);
                }
                if(cur % 3 == 0){
                    q.offer(cur / 3);
                }
                if(cur % 2 == 0){
                    q.offer(cur / 2);
                }
                q.offer(cur - 1);
            }
        }

        return res;

    }
}
*/
