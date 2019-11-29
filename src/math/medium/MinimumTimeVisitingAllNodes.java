/*

On a plane there are n points with integer coordinates points[i] = [xi, yi]. Your task is to find the minimum time in seconds to visit all points.

You can move according to the next rules:

In one second always you can either move vertically, horizontally by one unit or diagonally (it means to move one unit vertically and one unit horizontally in one second).
You have to visit the points in the same order as they appear in the array.


Input: points = [[1,1],[3,4],[-1,0]]
Output: 7
Explanation: One optimal path is [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
Time from [1,1] to [3,4] = 3 seconds
Time from [3,4] to [-1,0] = 4 seconds
Total time = 7 seconds
Example 2:

Input: points = [[3,2],[-2,2]]
Output: 5


Constraints:

points.length == n
1 <= n <= 100
points[i].length == 2
-1000 <= points[i][0], points[i][1] <= 1000

 */
package math.medium;

public class MinimumTimeVisitingAllNodes {

    public int minTimeToVisitAllPoints(int[][] points) {
        int n = points.length;
        int sum=0;
        for(int i=0;i<n-1;i++) {
            sum+=Math.max(Math.abs(points[i][0]-points[i+1][0]),Math.abs(points[i][1]-points[i+1][1]));
        }
        return sum;
    }

}

/*

Proof: the time cost to travel between 2 neighboring points equals the larger value between the absolute values of the difference of respective x and y coordinates of the 2 points.

a) Consider 2 points (x1, y1) and (x2, y2), let dx = |x1 - x2| and dy = |y1 - y2|;
According to the constraints of the problem, each step at most moves 1 unit along x and/or y coordinate.
Therefore, min time >= max(dx, dy);

b) On the other hand, each step can move 1 unit along x/y coordinate to cover the distance dx/dy, whichever is greater.
Combine the above a) and b), we complete the proof.

End of Proof

Traverse the input array, for each pair of neighboring points,
comparing the absolute difference in x and y coordinates, the larger value is the minimum time need to travel between them;
Sum these time.

 */