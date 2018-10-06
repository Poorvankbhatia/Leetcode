/*

On an infinite number line (x-axis), we drop given squares in the order they are given.

The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being positions[i][0] and sidelength positions[i][1].

The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently landed squares. We wait for each square to stick before dropping the next.

The squares are infinitely sticky on their bottom edge, and will remain fixed to any positive length surface they touch (either the number line or another square). Squares dropped adjacent to each other will not stick together prematurely.


Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped, after dropping squares represented by positions[0], positions[1], ..., positions[i].

Example 1:
Input: [[1, 2], [2, 3], [6, 1]]
Output: [2, 5, 5]
Explanation:

After the first drop of positions[0] = [1, 2]:
_aa
_aa
-------
The maximum height of any square is 2.


After the second drop of positions[1] = [2, 3]:
__aaa
__aaa
__aaa
_aa__
_aa__
--------------
The maximum height of any square is 5.
The larger square stays on top of the smaller square despite where its center
of gravity is, because squares are infinitely sticky on their bottom edge.


After the third drop of positions[1] = [6, 1]:
__aaa
__aaa
__aaa
_aa
_aa___a
--------------
The maximum height of any square is still 5.

Thus, we return an answer of [2, 5, 5].


Example 2:
Input: [[100, 100], [200, 100]]
Output: [100, 100]
Explanation: Adjacent squares don't get stuck prematurely - only their bottom edge can stick to surfaces.
Note:

1 <= positions.length <= 1000.
1 <= positions[i][0] <= 10^8.
1 <= positions[i][1] <= 10^6.

 */
package tree.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 06/10/18.
 */
public class FallingSquares {

    class Node {
        public int l;
        public int r;
        public int max;
        public int height;
        public Node left;
        public Node right;

        public Node (int l, int r, int max, int height) {
            this.l = l;
            this.r = r;
            this.max = max;
            this.height = height;
        }
    }


    private boolean intersect(Node n, int l, int r) {
        if (r <= n.l || l >= n.r) {
            return false;
        }
        return true;
    }

    private Node insert(Node root, int l, int r, int height) {
        if (root == null) {
            return new Node(l, r, r, height);
        }

        if (l <= root.l) {
            root.left = insert(root.left, l, r, height);
        } else {
            // l > root.l
            root.right = insert(root.right, l, r, height);
        }
        root.max = Math.max(r, root.max);
        return root;
    }

    // return the max height for interval (l, r)
    private int maxHeight(Node root, int l, int r) {
        if (root == null || l >= root.max) {
            return 0;
        }

        int res = 0;
        if (intersect(root, l, r)) {
            res = root.height;
        }
        if (r > root.l) {
            res = Math.max(res, maxHeight(root.right, l, r));
        }
        res = Math.max(res, maxHeight(root.left, l, r));
        return res;
    }

    public List<Integer> fallingSquares(int[][] positions) {
        Node root = null;
        List<Integer> res = new ArrayList<>();
        int prev = 0;

        for (int[] position : positions) {
            int l = position[0];
            int r = position[0] + position[1];
            int currentHeight = maxHeight(root, l, r);
            root = insert(root, l, r, currentHeight + position[1]);
            prev = Math.max(prev, currentHeight + position[1]);
            res.add(prev);
        }

        return res;
    }

}
