/*

Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.

 */
package hashing.hard;

import java.util.HashSet;

/**
 * Created by poorvank on 20/10/16.
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (Integer n : nums) {
            set.add(n);
        }

        int max = 1;
        for (int num : nums) {

            int left = num - 1;
            int right = num + 1;
            int count = 1;

            while (set.contains(left)) {
                set.remove(left);
                count++;
                left--;
            }

            while (set.contains(right)) {
                set.remove(right);
                count++;
                right++;
            }

            max = Math.max(count, max);

        }

        return max;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{-1,1,2,0};
        System.out.print(new LongestConsecutiveSequence().longestConsecutive(arr));
    }

}
