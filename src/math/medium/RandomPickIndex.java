/*

Given an array of integers with possible duplicates, randomly output the index of a given target number.
You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);


 */
package math.medium;

import java.util.Random;

/**
 * Created by poorvank.b on 18/04/18.
 */
public class RandomPickIndex {

    public class Solution {

        int[] a;
        private Random random;

        public Solution(int[] nums) {
            a= nums;
            random = new Random();
        }

        public int pick(int target) {
            int count = 0;
            for (int anA : a) {
                if (target == anA) {
                    count++;
                }
            }
            int pickIndex = random.nextInt(count);
            for(int i = 0; i < a.length; i++) {
                if(target == a[i]) {
                    if(pickIndex == 0) {
                        return i;
                    }
                    pickIndex--;
                }
            }
            return 0; // shouldn't come here
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4,5,7,4,4,2};
        System.out.println(new RandomPickIndex().new Solution(arr).pick(4));
    }

}

// O(1) init, O(1) memory, but O(N) to pick.

/*

int[] nums;
    Random rand;
    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }
     public int pick(int target) {
        int result = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target)
                continue;
            if (rand.nextInt(++count) == count-1)
                result = i;
        }

        return result;
    }

 */

/*

O(N) memory, O(N) init, O(1) pick.
private class Solution {

        private Map<Integer,List<Integer>> map = new HashMap<>();

        public Solution(int[] nums) {
            for (int i=0;i<nums.length;i++) {
                int num = nums[i];
                if(!map.containsKey(num)) {
                    map.put(num,new ArrayList<>());
                }
                map.get(num).add(i);
            }
        }

        public int pick(int target) {
            List<Integer> list = map.get(target);
            int i = (int) (Math.random()*list.size());
            return list.get(i);
        }

    }


    I hope it'll help.

At first, let's get clear that count is used to count the target number in nums. Say we now we have nums=[1,5,5,6,5,7,9,0] and the target is 5.

Now let's focus on the loop. When i=1, we get the first target number, and by rnd.nextInt(++count) we select a random number between
[0, 1), which means actually you could only select 0, so the probability of making result = 1 is 1.

Keep going. In the loop where i = 2, we get the second number. Now we have to get a random number in {0,1}. So what should we do if we
want to keep result = 1? It's simple: we have to make sure that, at this time, the random number generated should be 1 rather than 0
(otherwise the value of result will be changed), so the probability of keeping result = 1 is 1 * 1/2.

It is similar when we get the third target number, i.e., i = 4. Now we have to get a random number in {0,1,2}. If we still wish to keep
result = 1, the only way is to randomly get number 1 or 2 rather than 0, and the probability is 2/3. So the total probability of keeping
result = 1 will be 1 * 1/2 * 2/3.

Since we have four target number 5 here, the final probability of keeping result = 1 would be 1 * 1/2 * 2/3 * 3/4 = 1/4, which means the
probability of picking index 0 is 1/4 as the problem required. The probability is the same if you wish to pick another index.

You may ask what is the probability of picking the last possible index 4? Well, it simpler. You may ignore all operations before the
loop where i = 4, and the only thing you have to do is to get the random number 0 among {0,1,2,3} in the loop where i = 4, so the
probability is exactly 1/4.

 To make sure probability is 1/n
Think of that: if there is only one digit, the only random number has to be 0.
How about two, three, four? in order to make sure we have equal probability,
0 is one good and valid indicator the rest number has same probability as first matches number.
Be aware that, random number has nothing to do with the number in the array.
We just use that random number to know whether current number has same probablity ..


http://blog.gaurav.im/2017/07/30/reservoir-sampling/


https://www.geeksforgeeks.org/reservoir-sampling/

 */