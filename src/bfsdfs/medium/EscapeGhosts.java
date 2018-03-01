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


The taxicab distance is the number of moves required to get from point A to point B in our grid.
It is calculated as dist(A, B) = abs(A.x - B.x) + abs(A.y - B.y).

Let's say we start at S, the ghost starts at G, the target is T, and the ghost first meets us at X.
This implies dist(G, X) <= dist(S, X), as the ghost must reach X before or at the time that we do.

Now, if the ghost travels from G to X and then to T, it will reach T at time dist(G, T) <= dist(G, X) + dist(X, T) <= dist(S, X) + dist(X, T).
 The first inequality is because of the triangle inequality that all distance metrics satisfy.

The above shows that it is at least as good for the ghost to just travel directly to the target: if it could reach us beforehand
(at X), it will also reach us if it goes to X then to T, and then it would reach us if it just went directly to T.

Also, if the ghost goes directly to the target, then a necessary condition is clearly that we get to the target before the ghost.

Once we can make the assumption that all parties just go directly to the target in the shortest time possible, the problem is greatly simplified.

 */