/*

Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Example:

Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
Output: 45

 */
package math.medium;

public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        if(C<E||G<A )
            return (G-E)*(H-F) + (C-A)*(D-B);

        if(D<F || H<B)
            return (G-E)*(H-F) + (C-A)*(D-B);

        int right = Math.min(C,G);
        int left = Math.max(A,E);
        int top = Math.min(H,D);
        int bottom = Math.max(F,B);

        return (G-E)*(H-F) + (C-A)*(D-B) - (right-left)*(top-bottom);
    }
}
