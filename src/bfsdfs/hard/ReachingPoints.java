/*

A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).

Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a
sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.

Examples:
Input: sx = 1, sy = 1, tx = 3, ty = 5
Output: True
Explanation:
One series of moves that transforms the starting point to the target is:
(1, 1) -> (1, 2)
(1, 2) -> (3, 2)
(3, 2) -> (3, 5)

Input: sx = 1, sy = 1, tx = 2, ty = 2
Output: False

Input: sx = 1, sy = 1, tx = 1, ty = 1
Output: True

Note:

sx, sy, tx, ty will all be integers in the range [1, 10^9].

 */
package bfsdfs.hard;

/**
 * Created by poorvank.b on 11/02/18.
 */
public class ReachingPoints {

    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        if(sx==tx && sy==ty) {
            return true;
        }
        if(sx>tx || sy>ty) {
            return false;
        }
        if(tx>=ty) {
            int jumpFactor = Math.max((tx-sx)/ty,1);
            return reachingPoints(sx,sy,tx-(jumpFactor)*ty,ty);
        } else {
            int jumpFactor = Math.max((ty-sy)/tx,1);
            return reachingPoints(sx,sy,tx,ty-(jumpFactor)*tx);
        }
    }

    public static void main(String[] args) {
        System.out.println(new ReachingPoints().reachingPoints(1,1,2,2));
    }

}

/*

The obvious way is to go with a DFS or BFS like approach. Starting from the point (sx, sy),
we can go in two possible directions (sx + sy, sy) and (sx, sx + sy) and then each of these points can go in
two possible directions and so on. Note that the paths will form a tree like structure as we are going in either
right or upward direction, so we will never encounter the same point twice along a path.

SEE Images


instead of jumping only ty from tx or tx from ty in one step,
how much can we jump and to what point, so that we can still reach (sx, sy) without overshooting it ?

Let's call this the 'jump_factor'.

tx−jump_factor∗ty>=sx for tx>=ty and

ty−jump_factor∗tx>=sy for tx<ty

Note that the jump factor cannot be 0, else we would be stuck in one place, hence that 'max' factor.


 */