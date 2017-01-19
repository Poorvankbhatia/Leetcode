/*

You are given an array of positive and negative integers. If a number n at an index is positive,
then move forward n steps. Conversely, if it's negative (-n), move backward n steps. Assume the first
element of the array is forward next to the last element, and the last element is backward next to the first element.
Determine if there is a loop in this array. A loop starts and ends at a particular index with more than 1 element along the loop.
The loop must be "forward" or "backward'.

Example 1: Given the array [2, -1, 1, 2, 2], there is a loop, from index 0 -> 2 -> 3 -> 0.

Example 2: Given the array [-1, 2], there is no loop.

Example 3: Given the array [2, 0, 2, 1, 3], return false since 0 is not supposed to appear in the array.

Can you do it in O(n) time complexity and O(1) space complexity?

 */

package arrays.medium;

/**
 * Created by poorvank on 09/11/16.
 */
public class CircularLoop {

    public boolean circularArrayLoop(int[] nums) {
        int size = nums.length;

        for (Integer e : nums) {
            if(e==0) {
                return false;
            }
        }

        for (int i=0;i<size;i++) {
            int slow = i;
            int fast = (((slow+nums[slow])%size)+size)%size;
            boolean direction = (nums[slow] > 0);

            if(slow==fast) { // consider 2,1
                nums[slow] = 0;
                continue;
            }

            while (slow!=fast && nums[fast]!=0) {

                // check if the current direction is same as the circle direction
                if(nums[fast]>0 != direction) {
                    break;
                }

                slow = ((slow+nums[slow])%size+size)%size;
                fast = ((fast+nums[fast])%size+size)%size;

                if(nums[fast] > 0 != direction)
                    break;
                fast = ((fast+nums[fast])%size+size)%size;

            }

            // avoid the circle index is circle itself
            if(fast == slow && fast != ((fast+nums[fast])%size+size)%size)
                return true;
            //set the current index as has been checked
            nums[i] = 0;

        }

        return false;
    }


}

/*

The idea is same as find a circle in LinkedList with two pointers slow and fast. But the question set the index
circle by himself wrong, so this need to avoid.
Details:
for each index, check if it has a circle, if no circle set this to zero. Since if this index can't be a circle,
then every index behind arrived here won't be a circle.
The other situation I have write in the comments.



 */