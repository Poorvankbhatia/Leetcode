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
import java.util.Objects;

/**
 * Created by poorvank.b on 05/12/18.
 */
public class MoveStones {

    private int count=0;
    private Map<String,String> parent = new HashMap<>();
    private Map<String,Integer> size = new HashMap<>();
    public int removeStones(int[][] stones) {
        count = stones.length;

        for(int[] stone : stones) {
            String s = stone[0]+" "+stone[1];
            parent.put(s,s);
            size.put(s,1);
        }

        for (int[] stone : stones) {
            for (int[] stone1 : stones) {
                if (stone[0] == stone1[0] || stone[1] == stone1[1]) {
                    union(parent, stone, stone1);
                }
            }
        }
        System.out.println(count);

        return stones.length-count;

    }

    private String find(String p) {
        while(!p.equals(parent.get(p))) {
            parent.put(parent.get(p),parent.get(parent.get(p)));
            p=parent.get(p);
        }
        return p;
    }

    private void union(Map<String,String> map,int[] stone1,int[] stone2) {
        String s1 = stone1[0]+" "+stone1[1];
        String s2 = stone2[0]+" "+stone2[1];

        String parent1 = find(s1);
        String parent2 = find(s2);

        if(Objects.equals(parent1, parent2)) {
            return;
        }

        if(size.get(parent1)>=size.get(parent2)) {
            parent.put(parent2,parent1);
            size.put(parent1,size.get(parent1)+size.get(parent2));
        } else {
            parent.put(parent1,parent2);
            size.put(parent2,size.get(parent2)+size.get(parent1));
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