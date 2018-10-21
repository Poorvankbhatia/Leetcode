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


        while (tx >= sx && ty >= sy) {
            if (tx == ty)  {
                break;
            }
            if (tx > ty) {
                if (ty > sy) {
                    tx %= ty;
                }
                else {
                    return (tx - sx) % ty == 0;
                }
            } else {
                if (tx > sx) {
                    ty %= tx;
                }
                else {
                    return (ty - sy) % tx == 0;
                }
            }
        }
        return (tx == sx && ty == sy);

    }

    public static void main(String[] args) {
        System.out.println(new ReachingPoints().reachingPoints(1,1,2,2));
    }

}

/*

we work backwards to find the answer, trying to transform the target point to the starting point via applying the parent operation
(x, y) -> (x-y, y) or (x, y-x) [depending on which one doesn't have negative coordinates.]

Say tx > ty. We know that the next parent operations will be to subtract ty from tx,
until such time that tx = tx % ty. When both tx > ty and ty > sy, we can perform
all these parent operations in one step, replacing while tx > ty: tx -= ty with tx %= ty.

Otherwise, if say tx > ty and ty <= sy, then we know ty will not be changing (it can only decrease).
Thus, only tx will change, and it can only change by subtracting by ty. Hence, (tx - sx) % ty == 0 is a necessary
and sufficient condition for the problem's answer to be True.

The analysis above was for the case tx > ty, but the case ty > tx is similar. When tx == ty, no more moves can be made.

Time Complexity: O(log(max(tx,ty))). The analysis is similar to the analysis of the
Euclidean algorithm, and we assume that the modulo operation can be done in O(1) time.

Space Complexity: O(1)O(1).

 */