/*

There is a garden with N slots. In each slot, there is a flower. The N flowers will bloom one by one in N days. In each day,
there will be exactly one flower blooming and it will be in the status of blooming since then.

Given an array flowers consists of number from 1 to N. Each number in the array represents the place where the flower will open in that day.

For example, flowers[i] = x means that the unique flower that blooms at day i will be at position x, where i and x will be in the range from 1 to N.

Also given an integer k, you need to output in which day there exists two flowers in the status of blooming, and also the number of flowers
between them is k and these flowers are not blooming.

If there isn't such day, output -1.

Example 1:
Input:
flowers: [1,3,2]
k: 1
Output: 2
Explanation: In the second day, the first and the third flower have become blooming.
Example 2:
Input:
flowers: [1,2,3]
k: 1
Output: -1

 */
package tree.hard;

/**
 * Created by poorvank.b on 03/05/18.
 */
public class KEmptySlots {

    /*public int kEmptySlots(int[] flowers, int k) {
        if (flowers == null || flowers.length < 1) {
            return -1;
        }

        *//*
        We'll maintain active, a sorted data structure containing every flower that has currently bloomed.
        When we add a flower to active, we should check it's lower and higher neighbors.
        If some neighbor satisfies the condition, we know the condition occurred first on this day.
         *//*

        TreeSet<Integer> bloomingTreeSet = new TreeSet<>();
        int day = 0;
        for(int flower: flowers) {
            day++;
            bloomingTreeSet.add(flower);
            Integer higher = bloomingTreeSet.higher(flower);
            Integer lower = bloomingTreeSet.lower(flower);
            if (higher != null && higher - flower - 1 == k ||
                    lower != null && flower - lower - 1 == k ) {
                return day;
            }
        }

        return -1;
    }*/


    public int kEmptySlots(int[] flowers, int k) {
        int[] days = new int[flowers.length];
        for (int i = 0; i < days.length; i++) {
            //day[i]=x then ith flower blooms on day x
            days[flowers[i] - 1] = i + 1;
        }
        // days = [6, 8, 9, 10, 2, 1, 5, 3, 4, 7] => flower with position 1 blooms at 6th day, flower with position 2 blooms at 8th day, flower with position 3 blooms at 9th day.. so on
        int left = 0;
        int right = k + 1;
        int res = Integer.MAX_VALUE;
        for (int i = 1; right < days.length; i++) {
            // current days[i] is valid, continue scanning
            if (days[i] > days[left] && days[i] > days[right]) {
                continue;
            }
            // reach boundary of sliding window, since previous number are all valid, record result
            if (i == right) {
                res = Math.min(res, Math.max(days[left],days[right]));
            }
            // not valid, move the sliding window
            left = i;
            right = left + k + 1;
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }



    public static void main(String[] args) {
        int[] arr =  new int[]{6,5,8,9,7,1,10,2,3,4};
        System.out.println(new KEmptySlots().kEmptySlots(arr,2));
    }

}

/*



-> flowers[i] = x should mean that the unique flower that blooms at day i+1 (not i) will be at position x.
-> If you can get multiple possible results, then you need to return the minimum one.

The idea is to use an array days[] to record each position's flower's blooming day.
That means days[i] is the blooming day of the flower in position i+1.
We just need to find a subarray days[left, left+1,..., left+k-1, right] which satisfies: for any i = left+1,..., left+k-1, we can have days[left] < days[i] && days[right] < days[i].
Then, the result is max(days[left], days[right]).

O(n)

    public int kEmptySlots(int[] flowers, int k) {
    int[] days = new int[flowers.length];
    for (int i = 0; i < days.length; i++) {
        days[flowers[i] - 1] = i + 1;
    }
    int left = 0;
    int right = k + 1;
    int res = Integer.MAX_VALUE;
    for (int i = 1; right < days.length; i++) {
        // current days[i] is valid, continue scanning
        if (days[i] > days[left] && days[i] > days[right]) {
            continue;
        }
       // reach boundary of sliding window, since previous number are all valid, record result
        if (i == right) {
            res = Math.min(res, Math.max(days[left],days[right]));
        }
        // not valid, move the sliding window
        left = i;
        right = left + k + 1;
    }
    return res == Integer.MAX_VALUE ? -1 : res;
}

 */
