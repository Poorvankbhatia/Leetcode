/*
We have n cities labeled from 1 to n. Two different cities with labels x and y are directly connected by a
bidirectional road if and only if x and y share a common divisor strictly greater than some threshold.
More formally, cities with labels x and y have a road between them if there exists an integer z such that
all of the following are true:

x % z == 0,
y % z == 0, and
z > threshold.
Given the two integers, n and threshold, and an array of queries, you must determine for each queries[i] = [ai, bi]
if cities ai and bi are connected directly or indirectly. (i.e. there is some path between them).

Return an array answer, where answer.length == queries.length and answer[i] is true if for the ith query, there
is a path between ai and bi, or answer[i] is false if there is no path.

Input: n = 6, threshold = 2, queries = [[1,4],[2,5],[3,6]]
Output: [false,false,true]
Explanation: The divisors for each number:
1:   1
2:   1, 2
3:   1, 3
4:   1, 2, 4
5:   1, 5
6:   1, 2, 3, 6
Using the underlined divisors above the threshold, only cities 3 and 6 share a common divisor, so they are the
only ones directly connected. The result of each query:
[1,4]   1 is not connected to 4
[2,5]   2 is not connected to 5
[3,6]   3 is connected to 6 through path 3--6

Input: n = 6, threshold = 0, queries = [[4,5],[3,4],[3,2],[2,6],[1,3]]
Output: [true,true,true,true,true]
Explanation: The divisors for each number are the same as the previous example. However, since the threshold is 0,
all divisors can be used. Since all numbers share 1 as a divisor, all cities are connected.

Input: n = 5, threshold = 1, queries = [[4,5],[4,5],[3,2],[2,3],[3,4]]
Output: [false,false,false,false,false]
Explanation: Only cities 2 and 4 share a common divisor 2 which is strictly greater than the threshold 1, so they are the only ones directly connected.
Please notice that there can be multiple queries for the same pair of nodes [x, y], and that the query [x, y] is equivalent to the query [y, x].


Constraints:

2 <= n <= 104
0 <= threshold <= n
1 <= queries.length <= 105
queries[i].length == 2
1 <= ai, bi <= cities
ai != bi


 */
package bfsdfs.hard;

import bfsdfs.UnionFind;

import java.util.*;

public class GraphConnectivityWithThreshold {

    public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        List<Boolean> result = new ArrayList<>();
        UnionFind unionFind = new UnionFind(n+1);

        for (int i = threshold + 1; i <= n; i++) {
            int j=1;
            while (i*j<=n) {
                unionFind.union(i*j,j);
                j++;
            }
        }
        for (int[] q : queries) {
            result.add(unionFind.isConnected(q[0],q[1]));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new GraphConnectivityWithThreshold().areConnected(26,3,new int[][]{{16,9}}));
    }

}

/*

Example
Input: n = 6, all pair of cities:

Let z is the common divisor strictly greater than some threshold
z = 1, x = [2, 3, 4, 5, 6]
z = 2, x = [4, 6]
z = 3, x = [6]
z = 4: x = []
z = 5, x = []
z = 6, x = []


So more efficient idea here is for every city with value greater than threshold , we connect it with other cities
that are its multiple in range less than n.

  Ex : Threshold = 2, n = 20 => start city = {3, 4, 5, ..... n}

  city : 3 -- 3*m , where m = 1,2,3 .... such that 3*m <= 20
  city : 4 -- 4*m , where m = 1,2,3 .... such that 4*m <= 20
  .
  .
  .
  .
  .

  In general, connect city i with its multiple
  -> i.e (i -- i*m) , j = 1,2,3.... such that i*m <= n
Now, that we have built the DSU with all possible connections made, we just need to find if pair of cities given in
query belong to same component or not. In this way we can surely say that there is direct/indirect path
between pair of cities given in query.

  i.e. parent(i) == parent(j), where queries[k] = {i, j}, and k = 0,1,.... queries.size()

 */