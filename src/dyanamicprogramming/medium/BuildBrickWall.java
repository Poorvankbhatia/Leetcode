/*

You are given integers height and width which specify the dimensions of a brick wall you are building.
You are also given a 0-indexed array of unique integers bricks, where the ith brick has a height of 1 and a
 width of bricks[i]. You have an infinite supply of each type of brick and bricks may not be rotated.

Each row in the wall must be exactly width units long. For the wall to be sturdy, adjacent rows in the wall
should not join bricks at the same location, except at the ends of the wall.

Return the number of ways to build a sturdy wall. Since the answer may be very large, return it modulo 109 + 7.

Input: height = 2, width = 3, bricks = [1,2]
Output: 2
Explanation:
The first two walls in the diagram show the only two ways to build a sturdy brick wall.
Note that the third wall in the diagram is not sturdy because adjacent rows join bricks 2 units from the left.
Example 2:

Input: height = 1, width = 1, bricks = [5]
Output: 0
Explanation:
There are no ways to build a sturdy wall because the only type of brick we have is longer than the width of the wall.


Constraints:

1 <= height <= 100
1 <= width <= 10
1 <= bricks.length <= 10
1 <= bricks[i] <= 10
All the values of bricks are unique.

 */
package dyanamicprogramming.medium;

import java.util.ArrayList;
import java.util.Arrays;

public class BuildBrickWall {

    public int buildWall(int height, int width, int[] bricks) {
        int mod = 1000000007;
        boolean[] hasBricks = new boolean[11];
        for (int brick : bricks) {
            hasBricks[brick] = true;
        }

        // Find all possible way to build one single layer:
        ArrayList<Integer> buildLayer = waysOfBuildLayer(width, hasBricks);
        if (height == 1) {
            return buildLayer.size();
        }

        // Create adjacent list:
        ArrayList<ArrayList<Integer>> nexts = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < buildLayer.size(); i++) {
            int split = buildLayer.get(i);
            ArrayList<Integer> next = new ArrayList<Integer>();
            for (int j = 0; j < buildLayer.size(); j++) {
                int nextSplit = buildLayer.get(j);
                if ((split & nextSplit) == 0) {
                    next.add(j);
                }
            }
            nexts.add(next);
        }

        // Build the first layer:
        int[] thisLayer = new int[buildLayer.size()];
        Arrays.fill(thisLayer, 1);

        // Build the wall layer by layer:
        for (int i = 1; i < height; i++) {
            int[] nextLayer = new int[buildLayer.size()];
            for (int j = 0; j < thisLayer.length; j++) {
                ArrayList<Integer> next = nexts.get(j);
                for (int nextSplit : next) {
                    nextLayer[nextSplit] = (nextLayer[nextSplit] + thisLayer[j]) % mod;
                }
            }
            thisLayer = nextLayer;
        }

        // Add all numbers of ways to build the last layer:
        int result = 0;
        for (int num : thisLayer) {
            result = (result + num) % mod;
        }
        return result;
    }

    private ArrayList<Integer> waysOfBuildLayer(int width, boolean[] bricks) {
        int waysOfSplit = 1 << (width - 1);
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < waysOfSplit; i++) {
            if (possibleToBuild(i, bricks, width, waysOfSplit)) result.add(i);
        }
        return result;
    }

    private boolean possibleToBuild(int split, boolean[] bricks, int width, int waysOfSplit) {
        int wall = split | waysOfSplit;
        int curr = 1;
        while (wall != 0) {
            if (wall % 2 == 1) {
                if (!bricks[curr]) return false;
                curr = 1;
            } else {
                curr++;
            }
            wall /= 2;
        }
        return true;
    }

}

/*

Build Single Layer
First of all, to figure out how many ways could we build a wall, we need to know how many ways could we built
ONE SINGLE LAYER. We could definitely get the answer of this subproblem by recursion & backtracking,
however, here I use bit manipulation instead. There are two reasons: 1. width is no greater than 10,
so the complexity of bitmasking here is not too much; 2. In the next part, we could quickly find out
if two configurations of bricks could be adjacent by bitmasking. Actually I tried both recursion and bit
manipulation in this step, and it seems that bit manipulation submissions are a little bit faster than recursion submissions .

We could uniquely represent a configuration of bricks with an integer. For example, when width = 9 there are 8
possible positions to put splits, the number of all combinations is 2^8=256.

--------  // eight possible position to put splits
Assume that we have bricks = [3] , then there is only one way to build one single layer:

[3,3,3]
i.e.

--|--|--  // put splits at the 3rd and 6th positions
And if we replace - and | with zero and one, we could get an integer:

00100100  // i.e. 36
So the question is, with given width and bricks, could we compute number of ways to build one layer? Since we know width is no greater than 10, we could just write a for loop from zero to 2^(width - 1) - 1, and for each number we verify it it is possible to build such a layer with given bricks.

    // To check if we have a brick with specific size faster
	// we create a boolean array with length 11.
	// e.g. if the original int[] bricks = [1,2,3], then the boolean array is
	// [false, true, true, true, false, false, false, false, false, false, false]
    private ArrayList<Integer> waysOfBuildLayer(int width, boolean[] bricks) {
        int waysOfSplit = 1 << (width - 1);
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < waysOfSplit; i++) {
            if (possibleToBuild(i, bricks, width, waysOfSplit)) result.add(i);
        }
        return result;
    }

    private boolean possibleToBuild(int split, boolean[] bricks, int width, int waysOfSplit) {
        int wall = split | waysOfSplit;
        int curr = 1;
        while (wall != 0) {
            if (wall % 2 == 1) {
                if (!bricks[curr]) return false;
                curr = 1;
            } else {
                curr++;
            }
            wall /= 2;
        }
        return true;
    }
What about the Next Layer?
The description said

For the wall to be sturdy, adjacent rows in the wall should not join bricks at the same location, except at the ends of the wall.
So how do we determine if two adjacent layers have bricks at the same location? We already know how to uniquely
represent a configuration of bricks with an integer. When both A and B have join bricks (splits) at location i,
that is, both of their i-th bits are 1, then A & B != 0. That is, when A and B have no join bricks at the same location,
except at the ends of the wall, A & B = 0.

For example, when width = 3 and bricks = [1, 2], there are 3 possible ways to build one layer:

A: [1,1,1]  ->  ||  ->  0b11  ->  3
B: [1,2]    ->  |-  ->  0b10  ->  2
C: [2,1]    ->  -|  ->  0b01  ->  1
Only B and C could be adjacent, since 1 & 2 = 0

Then we could create a graph such that for every edge e = (u, v), u and v could be adjacent.
Here I use a little trick to make our code faster: Instead of creating a Map<Integer, List<Integer>>,
I create an ArrayList<ArrayList<Integer>>. Assume that we already have waysOfBuildLayer = [L_0, L_1, ..., L_(n-1)]
n ways to build one layer, if we use map for this graph, the map will be like:

{
   L_0: [...],
   L_1: [...],
   ...
   L_(n-1): [...]
}
However, if we choose ArrayList<ArrayList<Integer>>, it will be like:

[
   [...],
   [...],
   ...
   [...]
]
such that for each list[i][j], we havewaysOfBuildLayer[i] and waysOfBuildLayer[list[i][j]] could be adjacent.
With this trick, accessing info of edges would be much faster,
and in the next part(using DP to build wall layer by layer) we could also use array for each layer instead of map.

        ArrayList<ArrayList<Integer>> nexts = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < buildLayer.size(); i++) {
            int split = buildLayer.get(i);
            ArrayList<Integer> next = new ArrayList<Integer>();
            for (int j = 0; j < buildLayer.size(); j++) {
                int nextSplit = buildLayer.get(j);
                if ((split & nextSplit) == 0) {
                    next.add(j);
                }
            }
            nexts.add(next);
        }
Build Wall Layer by Layer, with Dynamic Programming
Assume that for each layer we have three ways to build it: A, B and C, only (B, C) and (C, B) could be adjacent

For layer 1, A, B and C are all possible, so we could create an array called thisLayer:

A	B	C
1	1	1
Then for layer 2,

A could not be adjacent with A, B or C, so nextLayer[A] = 0;
B could be adjacent with C only, so nextLayer[B] = thisLayer[C];
C could be adjacent with B only, so nextLayer[C] = thisLayer[B];
Then we get nextLayer:

A	B	C
0	1	1
Then we replace thisLayer with nextLayer and use similar approach to compute layer 3:

A	B	C
0	1	1
Repeat the step above to compute each layer, until we reach the last layer.

        // Build first layer:
		int[] thisLayer = new int[buildLayer.size()];
        Arrays.fill(thisLayer, 1);

        for (int i = 1; i < height; i++) {
		    // build next layer
            int[] nextLayer = new int[buildLayer.size()];
            for (int j = 0; j < thisLayer.length; j++) {
                ArrayList<Integer> next = nexts.get(j);
                for (int nextSplit : next) {
                    nextLayer[nextSplit] = (nextLayer[nextSplit] + thisLayer[j]) % mod;
                }
            }
            thisLayer = nextLayer;
        }
After getting the last layer, sum up every number in layer and get the result:

        int result = 0;
        for (int num : thisLayer) {
            result = (result + num) % mod;
        }
        return result;


Complexity
The time complexity of function possibleToBuild(int split, boolean[] bricks, int width, int waysOfSplit) is O(width).

The time complexity of function waysOfBuildLayer(int width, boolean[] bricks) is O(width * 2^(width - 1)). And the space complexity of its result, in worst case, could be O(2^(width - 1)). Since we know width is always no greater than 10, O(width * 2^(width - 1)) is still acceptable.

The time complexity of buildWall(int height, int width, int[] bricks) depends on number of ways to build one single layer, let's say N. The worst case is N = 2^(width - 1). For example, when width = 10 and bricks = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], wherever we put split(s), it is always possible to build such a layer.

After getting all possible ways of building one layer ArrayList<Integer> buildLayer, we build a graph ArrayList<ArrayList<Integer>> nexts such that for all nexts[i][j], buildLayer[i] and buildLayer[nexts[i][j]] could be adjacent. So how large could nexts be? The answer is O(3^(width - 1)).

Why? Assume we have an n-bit number X and k bits of it are zeros, then we could find 2^ k numbers Y such that X & Y = 0. And there are C(n, k) n-bit numbers with k ones. Thus, for 0 <= X, Y < 2^n, we could find

C(n, 0) * 2^0 + C(n, 1) * 2^1 + ... + C(n, k) * 2^k + ... + C(n, n) * 2^n
= C(n, 0) * 2^0 * 1^n + C(n, 1) * 2^1 * 1^(n-1) + ... + C(n, k) * 2^k * 1^(n-k) + ... + C(n, n) * 2^n * 1^0
= (2 + 1)^n
= 3^n
pairs of (X, Y) such that X & Y = 0

After getting the graph nexts, we update information layer by layer. For each layer, the time complexity is also O(3^(width - 1)).

Overall time complexity (worst case): O(width * 2^(width - 1) + height * 3^(width - 1))
Overall space complexity (worst case): O(2^(width - 1) + 3^(width - 1))

 */