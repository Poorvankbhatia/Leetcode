/**

 There are n buildings in a line. You are given an integer array heights of size n that represents the heights of
 the buildings in the line.

 The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without
 obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

 Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.



 Example 1:

 Input: heights = [4,2,3,1]
 Output: [0,2,3]
 Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
 Example 2:

 Input: heights = [4,3,2,1]
 Output: [0,1,2,3]
 Explanation: All the buildings have an ocean view.
 Example 3:

 Input: heights = [1,3,2,4]
 Output: [3]
 Explanation: Only building 3 has an ocean view.


 Constraints:

 1 <= heights.length <= 105
 1 <= heights[i] <= 109

 */

package stack.medium;

import java.util.Stack;

public class BuildWithOceanView {

    public int[] findBuildings(int[] heights) {
        // if can be viewed mark as true
        boolean[] canView = new boolean[heights.length];
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=heights.length-1;i>=0;i--) {
            while(!stack.isEmpty() && heights[stack.peek()]<heights[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                count++;
                stack.push(i);
                canView[i] = true;
            }
        }
        int[] result = new int[count];
        int i=0;
        // get all the buildings for which it is marked as true.
        for(int j=0;j<heights.length;j++) {
            if(canView[j]) result[i++]=j;
        }
        return result;
    }

}

/*

Better sol:


 public int[] findBuildings(int[] heights) {
        List<Integer> ls = new ArrayList<>();
        int last = Integer.MIN_VALUE;
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > last) {
                ls.add(i);
                last = heights[i];
            }
        }

        int index = 0;
        int[] res = new int[ls.size()];
        for (int i = ls.size() - 1; i >= 0; i--)
            res[index++] = ls.get(i);

        return res;
    }

 */