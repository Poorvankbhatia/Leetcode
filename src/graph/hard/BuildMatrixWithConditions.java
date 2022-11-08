/*
You are given a positive integer k. You are also given:

a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
The two arrays contain integers from 1 to k.

You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once.
The remaining cells should have the value 0.

The matrix should also satisfy the following conditions:

The number abovei should appear in a row that is strictly above the row at which the number belowi appears for all i from 0 to n - 1.
The number lefti should appear in a column that is strictly left of the column at which the number righti appears for all i from 0 to m - 1.
Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.

Input: k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
Output: [[3,0,0],[0,0,1],[0,2,0]]
Explanation: The diagram above shows a valid example of a matrix that satisfies all the conditions.
The row conditions are the following:
- Number 1 is in row 1, and number 2 is in row 2, so 1 is above 2 in the matrix.
- Number 3 is in row 0, and number 2 is in row 2, so 3 is above 2 in the matrix.
The column conditions are the following:
- Number 2 is in column 1, and number 1 is in column 2, so 2 is left of 1 in the matrix.
- Number 3 is in column 0, and number 2 is in column 1, so 3 is left of 2 in the matrix.
Note that there may be multiple correct answers.
Example 2:

Input: k = 3, rowConditions = [[1,2],[2,3],[3,1],[2,3]], colConditions = [[2,1]]
Output: []
Explanation: From the first two conditions, 3 has to be below 1 but the third conditions needs 3 to be above 1 to be satisfied.
No matrix can satisfy all the conditions, so we return the empty matrix.


Constraints:

2 <= k <= 400
1 <= rowConditions.length, colConditions.length <= 104
rowConditions[i].length == colConditions[i].length == 2
1 <= abovei, belowi, lefti, righti <= k
abovei != belowi
lefti != righti

 */
package graph.hard;

import java.util.*;

public class BuildMatrixWithConditions {

    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {

        int[] rowIndegree = new int[k];
        int[] colIndegree = new int[k];
        Map<Integer, List<Integer>> rowSeq = new HashMap<>();
        Map<Integer,List<Integer>> colSeq = new HashMap<>();

        for (int[] rowCondition : rowConditions) {
            rowSeq.putIfAbsent(rowCondition[0]-1,new ArrayList<>());
            rowSeq.get(rowCondition[0]-1).add(rowCondition[1]-1);
            rowIndegree[rowCondition[1]-1]++;
        }

        for (int[] colCondition : colConditions) {
            colSeq.putIfAbsent(colCondition[0]-1,new ArrayList<>());
            colSeq.get(colCondition[0]-1).add(colCondition[1]-1);
            colIndegree[colCondition[1]-1]++;
        }

        int[] rowRank = rank(rowIndegree,rowSeq);
        int[] colRank = rank(colIndegree,colSeq);

        if(rowRank==null || colRank==null) {
            return new int[0][0];
        }

        int[][] result = new int[k][k];

        for (int i=0;i<rowRank.length;i++) {
            result[rowRank[i]][colRank[i]] = i+1;
        }

        return result;
    }

    private int[] rank(int[] indegree, Map<Integer,List<Integer>> seq) {
        int count = indegree.length;
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0;i<indegree.length;i++) {
            if(indegree[i]==0) queue.add(i);
        }
        int[] rank = new int[indegree.length];
        int r=0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                int pop = queue.poll();
                count--;
                rank[pop] = r++;
                if (seq.containsKey(pop)) {
                    for (int next : seq.get(pop)) {
                        indegree[next]--;
                        if(indegree[next]==0){
                            queue.add(next);
                        }
                    }
                }
            }
        }
        return count==0?rank:null;
    }

    public static void main(String[] args) {
        int[][] rc = new int[][]{{1,2},{7,3},{4,3},{5,8},{7,8},{8,2},{5,8},{3,2},{1,3},{7,6},{4,3},{7,4},{4,8},{7,3},{7,5}};
        int[][] cc = new int[][]{{5,7},{2,7},{4,3},{6,7},{4,3},{2,3},{6,2}};
        System.out.println(Arrays.toString(new BuildMatrixWithConditions().buildMatrix(8,rc,cc)));
    }

}

/*

Simple Topological Sort.
Assign rank while calculating row sort and col sort.

 */