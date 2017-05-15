/*

Given a list of points that form a polygon when joined sequentially, find if this polygon is convex (Convex polygon definition).

Note:

There are at least 3 and at most 10,000 points.
Coordinates are in the range -10,000 to 10,000.
You may assume the polygon formed by given points is always a simple polygon (Simple polygon definition).
In other words, we ensure that exactly two edges intersect at each vertex, and that edges otherwise don't intersect each other.



Example 1:

[[0,0],[0,1],[1,1],[1,0]]

Answer: True


 */
package math.medium;

import java.util.List;

/**
 * Created by poorvank on 15/05/17.
 */

//Copied
public class ConvexPolygon {

    public boolean isConvex(List<List<Integer>> points) {
        // For each set of three adjacent points A, B, C, find the cross product AB · BC. If the sign of
        // all the cross products is the same, the angles are all positive or negative (depending on the
        // order in which we visit them) so the polygon is convex.
        boolean gotNegative = false;
        boolean gotPositive = false;
        int numPoints = points.size();
        int B, C;
        for (int A = 0; A < numPoints; A++) {
            // Trick to calc the last 3 points: n - 1, 0 and 1.
            B = (A + 1) % numPoints;
            C = (B + 1) % numPoints;

            int crossProduct =
                    crossProductLength(
                            points.get(A).get(0), points.get(A).get(1),
                            points.get(B).get(0), points.get(B).get(1),
                            points.get(C).get(0), points.get(C).get(1));
            if (crossProduct < 0) {
                gotNegative = true;
            }
            else if (crossProduct > 0) {
                gotPositive = true;
            }
            if (gotNegative && gotPositive) return false;
        }

        // If we got this far, the polygon is convex.
        return true;
    }

    // Return the cross product AB x BC.
    // The cross product is a vector perpendicular to AB and BC having length |AB| * |BC| * Sin(theta) and
    // with direction given by the right-hand rule. For two vectors in the X-Y plane, the result is a
    // vector with X and Y components 0 so the Z component gives the vector's length and direction.
    private int crossProductLength(int Ax, int Ay, int Bx, int By, int Cx, int Cy)
    {
        // Get the vectors' coordinates.
        int BAx = Ax - Bx;
        int BAy = Ay - By;
        int BCx = Cx - Bx;
        int BCy = Cy - By;

        // Calculate the Z coordinate of the cross product.
        return (BAx * BCy - BAy * BCx);
    }

}

/*

Geometrically the cross product produces a vector that is orthogonal to the two vectors used for the calculation,
as both of your vectors lie in the XY plane the result will only have a Z component.
The Sign of that z component denotes wether that vector is looking up or down on the XY plane.
That sign is dependend on AB being in clockwise or counter clockwise order from each other.
That in turn means that the sign of z component shows you if the point you are looking at lies to the left or the right of the line that is on AB.

So with the crossproduct of two vectors A and B being the vector

AxB = (AyBz − AzBy, AzBx − AxBz, AxBy − AyBx)
with Az and Bz being zero you are left with the third component of that vector

AxBy - AyBx
With A being the vector from point a to b, and B being the vector from point a to c means

Ax = (b[x]-a[x])
Ay = (b[y]-a[y])
Bx = (c[x]-a[x])
By = (c[y]-a[y])
giving

AxBy - AyBx = (b[x]-a[x])*(c[y]-a[y])-(b[y]-a[y])*(c[x]-a[x])

 */