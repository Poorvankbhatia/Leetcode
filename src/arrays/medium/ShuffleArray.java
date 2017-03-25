/*

Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

 */

package arrays.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by poorvank.b on 25/03/17.
 */
public class ShuffleArray {

    private int[] originalArr;
    private Map<Integer,Integer> map;
    public ShuffleArray(int[] nums) {
        originalArr = nums;
        map = new HashMap<>();
        for (int i=0;i<nums.length;i++) {
            map.put(i,nums[i]);
        }
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
       return originalArr;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] shuffledArr = new int[originalArr.length];
        for (int i=0;i<shuffledArr.length;i++) {
            int max = shuffledArr.length-1;
            Random random = new Random();
            int index = i + random.nextInt(max-i+1);
            shuffledArr[i] = originalArr[index];
            originalArr[index] = originalArr[i];
        }

        for (Integer key : map.keySet()) {
            originalArr[key] = map.get(key);
        }
        return shuffledArr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3};
        ShuffleArray s = new ShuffleArray(arr);
        System.out.print(Arrays.toString(s.shuffle()));
        System.out.print(Arrays.toString(s.reset()));

    }

}


/*

https://spin.atomicobject.com/2014/08/11/fisher-yates-shuffle-randomization-algorithm/

 */