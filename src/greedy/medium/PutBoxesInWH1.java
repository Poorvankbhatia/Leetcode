/*

You are given two arrays of positive integers, boxes and warehouse, representing the heights of some boxes of unit
width and the heights of n rooms in a warehouse respectively. The warehouse's rooms are labelled from 0 to n - 1
from left to right where warehouse[i] (0-indexed) is the height of the ith room.

Boxes are put into the warehouse by the following rules:

Boxes cannot be stacked.
You can rearrange the insertion order of the boxes.
Boxes can only be pushed into the warehouse from left to right only.
If the height of some room in the warehouse is less than the height of a box, then that box and all other boxes behind it will be stopped before that room.
Return the maximum number of boxes you can put into the warehouse.

Input: boxes = [4,3,4,1], warehouse = [5,3,3,4,1]
Output: 3
Explanation:

We can first put the box of height 1 in room 4. Then we can put the box of height 3 in either of the 3 rooms 1, 2, or 3.
 Lastly, we can put one box of height 4 in room 0.
There is no way we can fit all 4 boxes in the warehouse.


Input: boxes = [1,2,2,3,4], warehouse = [3,4,1,2]
Output: 3
Explanation:

Notice that it's not possible to put the box of height 4 into the warehouse since it cannot pass the first room of height 3.
Also, for the last two rooms, 2 and 3, only boxes of height 1 can fit.
We can fit 3 boxes maximum as shown above. The yellow box can also be put in room 2 instead.
Swapping the orange and green boxes is also valid, or swapping one of them with the red box.

 */
package greedy.medium;

import java.util.Arrays;

public class PutBoxesInWH1 {

    public int maxBoxesInWarehouse(int[] boxes, int[] warehouse) {
// Preprocess the height of the warehouse rooms to get usable heights
        for (int i = 1; i < warehouse.length; i++) {
            warehouse[i] = Math.min(warehouse[i - 1], warehouse[i]);
        }

        // Iterate through boxes from smallest to largest
        Arrays.sort(boxes);

        int count = 0;

        for (int i = warehouse.length - 1; i >= 0; i--) {
            // Count the boxes that can fit in the current warehouse room
            if (count < boxes.length && boxes[count] <= warehouse[i]) {
                count++;
            }
        }

        return count;
    }

}

/*

Arrays.sort(boxes);
        int m = boxes.length;
        int n = wh.length;
        int res = 0;
        for(int i = 0;i < m & res<n;i++){
            if(boxes[m-i-1] <= wh[res]) {
                res++;
            }
        }
        return res;

O(nlog(n)+m)

The intuition is that if each step follows the optimal strategy, then the overall arrangement of boxes will be optimal.

Imagine we have a box of height h, and we want to push it into the warehouse. We start pushing from the left, and we
want to push it as far right as we can. The limiting factor on how far we can push it will be the first position in
the warehouse we encounter that has a height less than h. We won't be able to push the box into this position, or
into any position after it.

To make the algorithm more efficient, we will first preprocess the heights of the warehouse. Keeping in mind that the
limiting factor for each position is the minimum height that comes before it, we update the height for each position
so that it is no higher than this minimum. This essentially changes the warehouse array to a weakly decreasing array.

We then sort the boxes from shortest to tallest. Then, we take the shortest box remaining and push it as far right as
possible through the warehouse (we have to stop when the next position is shorter than this box).


Because lower heights for rooms on the left will block the entry of boxes into rooms on the right,
we need to preprocess the array of warehouse heights such that it becomes a non-increasing sequence.
Then, we start from the smallest box and the rightmost position of the warehouse.
If the current box can fit in the warehouse room, we increment the count by 1 and
move on to the next box. Otherwise, we move on to the next warehouse room and check if the box will fit there.




 */