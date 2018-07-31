/*

Given scores of N athletes, find their relative ranks and the people with the top three highest scores,
who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".

Example 1:
Input: [5, 4, 3, 2, 1]
Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal".
For the left two athletes, you just need to output their relative ranks according to their scores.
Note:
N is a positive integer and won't exceed 10,000.
All the scores of athletes are guaranteed to be unique.

 */
package heap.medium;

import java.util.PriorityQueue;

/**
 * Created by poorvank.b on 03/06/18.
 */
public class RelativeRanks {

    public String[] findRelativeRanks(int[] nums) {
        String[] result = new String[nums.length];
        PriorityQueue<Integer> queue
                = new PriorityQueue<>(nums.length, (i, j) -> -1*(nums[i] - nums[j]));

        for(int i = 0; i < nums.length; i++) {
            queue.offer(i);
        }

        try {
            result[queue.poll()] = "Gold Medal";
            result[queue.poll()] = "Silver Medal";
            result[queue.poll()] = "Bronze Medal";
        } catch (Exception e) {

        }

        int rank = 4;
        while(!queue.isEmpty()) {
            int index = queue.poll();
            result[index] = String.valueOf(rank);
            rank++;
        }

        return result;
    }

}

/*
 G I
 */