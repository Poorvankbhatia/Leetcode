/*

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 */

package dyanamicprogramming.medium;

import java.util.List;
import static java.util.Arrays.*;

/**
 * Created by poorvank on 14/12/16.
 */
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {

        int n =  triangle.size();
        if(n==0) {
            return 0;
        }
        List<Integer> lastList = triangle.get(n-1);
        int[] minArr = new int[lastList.size()];


        for (int i=0;i<minArr.length;i++) {
            minArr[i] = lastList.get(i);
        }

        for (int j=n-2;j>=0;j--) {
            for (int i=0;i<triangle.get(j).size();i++) {
                minArr[i] = triangle.get(j).get(i) + Math.min(minArr[i],minArr[i+1]);
            }
        }

        return minArr[0];


        /*int min = triangle.get(0).get(0);
        return minimumTotalRecursive(minArr,triangle,0,1,min);*/



    }


    private int minimumTotalRecursive(int[] minArr, List<List<Integer>> triangle, int minIndex, int listIndex, int min) {

        if(listIndex==triangle.size()) {
            return min;
        }


        List<Integer> nextList = triangle.get(listIndex);
        int adjacentNo1 = minimumTotalRecursive(minArr, triangle, minIndex, listIndex + 1, nextList.get(minIndex));
        int adjacentNo2 = minimumTotalRecursive(minArr, triangle, minIndex + 1, listIndex + 1, nextList.get(minIndex + 1));
        minArr[listIndex] = min + Math.min(adjacentNo1,adjacentNo2);


        return minArr[listIndex];

    }


    public static void main(String[] args) {
        List<List<Integer>> lists = asList(
                asList( -1 ),
                asList( 2,3 ),
                asList( 1,-1,-3 ));

        System.out.println(new Triangle().minimumTotal(lists));

    }

}

/*

Overlapping subproblems:

     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]

Once we calculate minimum sum from 5 in row 3, we dont need to calculate it again 2 times for 3 & 4 in row 2.

 The triangle has a tree-like structure, which would lead people to think about traversal algorithms such as DFS. However,
 if you look closely, you would notice that the adjacent nodes always share a 'branch'. In other word, there are overlapping subproblems.
 Also, suppose x and y are 'children' of k. Once minimum paths from x and y to the
  bottom are known, the minimum path starting from k can be decided in O(1), that is optimal substructure.
  Therefore, dynamic programming would be the best solution to this problem in terms of time complexity.

The difference between 'top-down' and 'bottom-up' DP can be
'literally' pictured in the input triangle. For 'top-down' DP, starting from the node on the very top, we recursively find the
minimum path sum of each node. When a path sum is calculated, we store it in an array (memoization); the next time we need to calculate
the path sum of the same node, just retrieve it from the array.

 */
