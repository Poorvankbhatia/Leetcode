/*
You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:

0 represents the obstacle can't be reached.
1 represents the ground can be walked through.
The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first.
 And after cutting, the original place has the tree will become a grass (value 1).

You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut
off all the trees, output -1 in that situation.

You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.

Example 1:
Input:
[
 [1,2,3],
 [0,0,4],
 [7,6,5]
]
Output: 6
Example 2:
Input:
[
 [1,2,3],
 [0,0,0],
 [7,6,5]
]
Output: -1
Example 3:
Input:
[
 [2,3,4],
 [0,0,5],
 [8,7,6]
]
Output: 6
Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
Hint: size of the given matrix will not exceed 50x50.
 */
package bfsdfs.hard;

import java.util.*;

/**
 * Created by poorvank.b on 10/09/17.
 */
public class CutOffTrees {

    private int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    public int cutOffTree(List<List<Integer>> forest) {

        if(forest==null || forest.size()==0) {
            return 0;
        }

        Map<Integer,Integer[]> map = new TreeMap<>();

        int m = forest.size();
        int n = forest.get(0).size();

        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(forest.get(i).get(j)>1) {
                    map.put(forest.get(i).get(j),new Integer[]{i,j});
                }
            }
        }

        int totalSteps = 0;
        Integer[] currentCoordinate = new Integer[]{0,0};
        for(Integer key : map.keySet()) {
            boolean[][] visited=new boolean[m][n];
            int steps = minStepsRequired(currentCoordinate,map.get(key),visited,m,n,forest);
            if(steps==-1) {
                return -1;
            }
            //System.out.println(steps);
            totalSteps+= steps;
            currentCoordinate = map.get(key);
        }

        return totalSteps;

    }

    private int minStepsRequired(Integer[] currentCoordinate,Integer[] finalCoordinate,boolean[][] visited,
                                 int m,int n,List<List<Integer>> forest) {

        Queue<Integer[]> queue = new LinkedList<>();
        queue.add(currentCoordinate);

        int stepCount=0;

        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for(int k=0;k<queueSize;k++) {
                Integer[] curr = queue.poll();
                if(Objects.equals(curr[0], finalCoordinate[0]) && Objects.equals(curr[1], finalCoordinate[1])) {
                    return stepCount;
                }

                for (int[] direction : directions) {
                    int nextX = curr[0]+direction[0];
                    int nextY = curr[1]+direction[1];
                    if(nextX>=0 && nextX<m && nextY>=0 && nextY<n && !visited[nextX][nextY] && forest.get(nextX).get(nextY)!=0) {
                        queue.add(new Integer[]{nextX,nextY});
                        visited[nextX][nextY]=true;
                    }
                }
            }
            stepCount++;
        }

        return -1;

    }

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1,2,0);
        List<Integer> list2 = Arrays.asList(3,4,0);
        List<Integer> list3 = Arrays.asList(7,5,6);
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(list1);lists.add(list2);lists.add(list3);
        System.out.println(new CutOffTrees().cutOffTree(lists));
    }

}

/*

Get a list of all trees to cut down in order of lowest height to greatest

Then go from one tree to the other starting at (0,0) to cut them all down.
Use BFS to calculate optimal number of steps needed to go from one tree to another.

 */