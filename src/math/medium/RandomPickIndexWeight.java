/*

Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex
which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input:
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input:
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]

 */

package math.medium;

import java.util.Random;
import java.util.TreeMap;

/**
 * Created by poorvank.b on 18/08/18.
 */
public class RandomPickIndexWeight {

    TreeMap<Integer, Integer> map;
    private int sum;
    private Random random;

    public RandomPickIndexWeight(int[] w) {
        map = new TreeMap<>();
        sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            map.put(sum, i);
        }
        random = new Random();
    }

    //In the case w = [1, 99] , pickIndex() should return 1 for 99% and 0 for 1%.
    private int pickIndex() {
        int key = map.ceilingKey(random.nextInt(sum));
        return map.get(key);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 3, 4};
        System.out.println(new RandomPickIndexWeight(arr).pickIndex());
    }

}

/*

Use accumulated freq array to get idx.
w[] = {2,5,3,4} => wsum[] = {2,7,10,14}
then get random val random.nextInt(14)+1, idx is in range [1,14]

idx in [1,2] return 0
idx in [3,7] return 1
idx in [8,10] return 2
idx in [11,14] return 3


 */