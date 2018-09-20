/*

You are playing a simplified Pacman game. You start at the point (0, 0), and your destination is (target[0], target[1]). There are several
ghosts on the map, the i-th ghost starts at (ghosts[i][0], ghosts[i][1]).

Each turn, you and all ghosts simultaneously *may* move in one of 4 cardinal directions: north, east, west, or south, going from the previous
point to a new point 1 unit of distance away.

You escape if and only if you can reach the target before any ghost reaches you (for any given moves the ghosts may take.)  If you reach any
square (including the target) at the same time as a ghost, it doesn't count as an escape.

Return True if and only if it is possible to escape.

Example 1:
Input:
ghosts = [[1, 0], [0, 3]]
target = [0, 1]
Output: true
Explanation:
You can directly reach the destination (0, 1) at time 1, while the ghosts located at (1, 0) or (0, 3) have no way to catch up with you.
Example 2:
Input:
ghosts = [[1, 0]]
target = [2, 0]
Output: false
Explanation:
You need to reach the destination (2, 0), but the ghost at (1, 0) lies between you and the destination.
Example 3:
Input:
ghosts = [[2, 0]]
target = [1, 0]
Output: false
Explanation:
The ghost can reach the target at the same time as you.
Note:

All points have coordinates with absolute value <= 10000.
The number of ghosts will not exceed 100.

 */
package bfsdfs.medium;

/**
 * Created by poorvank.b on 27/02/18.
 */
public class EscapeGhosts {

    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int max = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] ghost : ghosts) {
            int d = Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1]);
            if (d <= max) return false;
        }
        return true;
    }

}

/*

Firstly, you calculate max, which is the Manhattan distance of the starting point to the target;
Next, for each of the ghosts, you calculate the Manhattan distance between that ghost and the target;
If this distance is smaller, then it means that this ghost would eat the pacman (because he would reach the target before the pacman)
 and hence we return false; else we return true.


Let's say you always take the shortest route to reach the target because if you go a longer or a more tortuous route, I believe the ghost has a better
chance of getting you.
Denote your starting point A, ghost's starting point B, the target point C.
For a ghost to intercept you, there has to be some point D on AC such that AD = DB. Fix D. By triangle inequality, AC = AD + DC = DB + DC >= BC.
What that means is if the ghost can intercept you in the middle, it can actually reach the target at least as early as you do. So wherever the ghost
starts at (and wherever the interception point is), its best chance of getting you is going directly to the target and waiting there rather than
intercepting you in the middle.

SEE IMAGES

 */