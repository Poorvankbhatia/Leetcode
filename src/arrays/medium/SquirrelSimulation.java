/*

There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D grid.
Your goal is to find the minimal distance for the squirrel to collect all the nuts and put them under the tree one by one.
The squirrel can only take at most one nut at one time and can move in four directions - up, down, left and right, to the adjacent cell.
 The distance is represented by the number of moves.

Example 1:
Input:
Height : 5
Width : 7
Tree position : [2,2]
Squirrel : [4,4]
Nuts : [[3,0], [2,5]]
Output: 12

 */
package arrays.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 07/05/17.
 */
public class SquirrelSimulation {

    private class Nut {
        int x;
        int y;
        int distanceFromTree;
        int distanceFromSquirrel;

        public Nut(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Nut{" +
                    "x=" + x +
                    ", y=" + y +
                    ", distanceFromTree=" + distanceFromTree +
                    ", distanceFromSquirrel=" + distanceFromSquirrel +
                    '}';
        }
    }

    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {

        List<Nut> list = new ArrayList<>();

        int totalSum =0;
        for (int[] nut1 : nuts) {
            Nut nut = new Nut(nut1[0], nut1[1]);
            nut.distanceFromTree = Math.abs(nut1[0] - tree[0]) + Math.abs(nut1[1] - tree[1]);
            nut.distanceFromSquirrel = Math.abs(nut1[0] - squirrel[0]) + Math.abs(nut1[1] - squirrel[1]);
            totalSum += nut.distanceFromTree*2;
            list.add(nut);
        }

        System.out.println(list.toString());

        int result = Integer.MAX_VALUE;

        for (Nut nut : list) {
            int dist = totalSum + nut.distanceFromSquirrel - nut.distanceFromTree;
            result = Math.min(result, dist);
        }

        return result;


    }

    public static void main(String[] args) {
        int x = new SquirrelSimulation().minDistance(5,7,new int[]{2,2},new int[]{4,4},new int[][]{{3,0},{2,5}});
        System.out.println(x);
    }

}


/*

Better Method:


public class Solution {
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int n = nuts.length;
        int[] nutToTree = new int[n];
        int[] nutToSquirrel = new int[n];

        int sum = 0;
        for (int i = 0; i < n; i++) {
            nutToTree[i] = Math.abs(nuts[i][0] - tree[0]) + Math.abs(nuts[i][1] - tree[1]);
            sum += nutToTree[i] * 2;
            nutToSquirrel[i] = Math.abs(nuts[i][0] - squirrel[0]) + Math.abs(nuts[i][1] - squirrel[1]);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int dist = sum + nutToSquirrel[i] - nutToTree[i];
            min = Math.min(min, dist);
        }

        return min;
    }
}

 */