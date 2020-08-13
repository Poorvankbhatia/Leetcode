/*
Given the integer n representing the number of courses at some university labeled from 1 to n, and the array dependencies
where dependencies[i] = [xi, yi]  represents a prerequisite relationship, that is, the course xi must be taken before the course yi.
 Also, you are given the integer k.

In one semester you can take at most k courses as long as you have taken all the prerequisites for the courses you are taking.

Return the minimum number of semesters to take all courses. It is guaranteed that you can take all courses in some way.

Input: n = 4, dependencies = [[2,1],[3,1],[1,4]], k = 2
Output: 3
Explanation: The figure above represents the given graph. In this case we can take courses 2 and 3 in the first semester,
then take course 1 in the second semester and finally take course 4 in the third semester.

Input: n = 5, dependencies = [[2,1],[3,1],[4,1],[1,5]], k = 2
Output: 4
Explanation: The figure above represents the given graph. In this case one optimal way to take all courses is: take courses 2 and 3 in the first semester and take course 4 in the second semester, then take course 1 in the third semester and finally take course 5 in the fourth semester.
Example 3:

Input: n = 11, dependencies = [], k = 2
Output: 6


Constraints:

1 <= n <= 15
1 <= k <= n
0 <= dependencies.length <= n * (n-1) / 2
dependencies[i].length == 2
1 <= xi, yi <= n
xi != yi
All prerequisite relationships are distinct, that is, dependencies[i] != dependencies[j].
The given graph is a directed acyclic graph.

 */
package dyanamicprogramming.hard;

import java.util.Arrays;

public class ParallelCourses2 {

    public int minNumberOfSemesters(int n, int[][] deps, int k) {
        int[] preq = new int[n];
        for (int[] dep : deps) {
            // to study j, what are the prerequisites?  each set bit is a class that we need to take. ith bit means ith class
            // -1 because classes are 1 to n
            preq[dep[1] - 1] |= 1 << (dep[0] - 1);
        }
        int[] dp = new int[1 << n];
        Arrays.fill(dp, n);
        dp[0] = 0;
        for (int i = 0; i < (1 << n); i++) {
            // we are now at status i. we can "influence" a later status from this status
            int canStudy = 0;   // what are the classes we can study?
            for (int j = 0; j < n; j++) {
                // a & b== b means b is a's subset
                // so if preq[j] is i's subset, we can now study j given status i
                if ((i & preq[j]) == preq[j]) {
                    canStudy |= (1 << j);
                }
            }
            canStudy &= ~i;
            // take out i, so that we only enumerate a subset canStudy without i.
            // note we will | later so here we need a set that has no intersection with i to reduce the enumeration cost
            for (int sub = canStudy; sub > 0; sub = (sub - 1) & canStudy) {
                // we can study one or more courses indicated by set "canStudy". we need to enumerate all non empty subset of it.
                // This for loop is a typical way to enumerate all subsets of a given set "canStudy"
                // we studied i using dp[i] semesters. now if we also study the subset sub, we need dp [i ]+1 semesters,
                // and the status we can "influence" is dp[ i | sub] because at that state, we studied what we want to study in "sub"
                if (Integer.bitCount(sub) <= k) {
                    dp[i | sub] = Math.min(dp[i | sub], dp[i] + 1);
                }
            }
        }
        return dp[(1 << n) - 1];
    }

    public static void main(String[] args) {
        int[][] d = new int[][]{
                {1,2},
                {1,3},
                {7,5},
                {7,6},
                {4,8},
                {8,9},
                {9,10},
                {10,11},
                {11,12}
        };
        System.out.println(new ParallelCourses2().minNumberOfSemesters(12,d,2));
    }

}

/*
DP + Bitmask
 */