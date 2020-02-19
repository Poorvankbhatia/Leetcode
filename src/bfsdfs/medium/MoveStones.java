/*

On a 2D plane, we place stones at some integer coordinate points.  Each coordinate point may have at most one stone.

Now, a move consists of removing a stone that shares a column or row with another stone on the grid.

What is the largest possible number of moves we can make?



Example 1:

Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Example 2:

Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Example 3:

Input: stones = [[0,0]]
Output: 0


Note:

1 <= stones.length <= 1000
0 <= stones[i][j] < 10000


 */
package bfsdfs.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 05/12/18.
 */
public class MoveStones {

    private Map<int[],int[]> parentMap = new HashMap<>();
    private Map<int[],Integer> sizeMap = new HashMap<>();
    private int count;
    public int removeStones(int[][] stones) {
        count = stones.length;
        for(int[] stone : stones) {
            parentMap.put(stone,stone);
            sizeMap.put(stone,1);
        }
        for(int[] s1 : stones) {
            for(int[] s2: stones) {
                if(s1[0]==s2[0] || s1[1]==s2[1]) {
                    union(s1,s2);
                }
            }
        }

        return stones.length-count;
    }

    private int[] parent(int[] p) {
        while(p!=parentMap.get(p)) {
            parentMap.put(p,parentMap.get(parentMap.get(p)));
            p = parentMap.get(p);
        }
        return p;
    }

    private void union(int[] a,int[] b) {
        int[] pA = parent(a);
        int[] pB = parent(b);

        if(pA==pB) {
            return;
        } else {
            if(sizeMap.get(pA)>=sizeMap.get(pB)) {
                parentMap.put(pB,pA);
                sizeMap.put(pA,sizeMap.get(pB)+1);
            } else {
                parentMap.put(pA,pB);
                sizeMap.put(pB,sizeMap.get(pA)+1);
            }
        }

        count--;

    }

    public static void main(String[] args) {
        int[][] stones = new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        System.out.println(new MoveStones().removeStones(stones));
    }

}

/*

If stone a and stone b are in the same column/row, we connect them as a component
One component can be removed to one stone left at least.
The largest possible number of moves we can make = Total stones count - count of stones left = Total stones count - count of components.

 */