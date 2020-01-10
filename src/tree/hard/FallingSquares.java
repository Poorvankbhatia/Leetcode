/*

On an infinite number line (x-axis), we drop given squares in the order they are given.

The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being positions[i][0] and
sidelength positions[i][1].

The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently landed squares.
We wait for each square to stick before dropping the next.

The squares are infinitely sticky on their bottom edge, and will remain fixed to any positive length surface they touch
(either the number line or another square). Squares dropped adjacent to each other will not stick together prematurely.


Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped,
after dropping squares represented by positions[0], positions[1], ..., positions[i].

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
    private class SegmentNode {
        private SegmentNode left;
        private SegmentNode right;
        private int[] range;
        private int height;
        private int lazy;

        public SegmentNode(int[] range, int height) {
            this.range = range;
            this.height = height;
        }
    }

    private SegmentNode root;
    public List<Integer> fallingSquares(int[][] positions) {
        root = new SegmentNode(new int[]{0,1000000000},0);
        List<Integer> result = new ArrayList<>();
        int best=0;
        for (int[] pos : positions) {
            int left = pos[0];
            int right = pos[0]+pos[1]-1;
            int h = query(root,left,right)+pos[1];
            best = Math.max(best,h);
            result.add(best);
            update(root,left,right,h);
        }
        return result;
    }

    private void update(SegmentNode node,int l,int r,int height) {
        if(node==null || l>node.range[1] || r<node.range[0]) {
            return;
        }
        if(l<=node.range[0] && node.range[1]<=r) {
            node.height=height;
            node.lazy=height;
            return;
        }
        normalize(node);
        update(node.left,l,r,height);
        update(node.right,l,r,height);
        node.height = Math.max(node.left.height,node.right.height);
    }

    private int query(SegmentNode node,int l,int r) {
        if(node==null || l>node.range[1] || r<node.range[0]) {
            return 0;
        }
        if(l<=node.range[0] && node.range[1]<=r) {
            return node.height;
        }
        normalize(node);
        return Math.max(query(node.left,l,r),query(node.right,l,r));
    }

    private void normalize(SegmentNode node) {
        if(node.range[0]<node.range[1]) {
            if(node.left==null || node.right==null) {
                int mid = node.range[0]+(node.range[1]-node.range[0])/2;
                node.left = new SegmentNode(new int[]{node.range[0],mid},node.height);
                node.right = new SegmentNode(new int[]{mid+1,node.range[1]},node.height);
            } else if(node.lazy>0) {
                node.left.height=node.lazy;
                node.right.height=node.lazy;
                node.left.lazy=node.lazy;
                node.right.lazy=node.lazy;
            }
        }
        node.lazy=0;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{9,7},{1,9},{3,1}};//[7,16,17]
        //int[][] a = new int[][]{{2,1},{2,9},{1,8}};
       // int[][] a = new int[][]{{6,4},{2,7},{6,9}}; //[4,11,20]
        //int[][] a = new int[][]{{4,9},{8,8},{6,8},{8,2},{1,2}}; //[9,17,25,27,27]
        System.out.println(new FallingSquares().fallingSquares(a));
    }

}

/*

SEGMENT TREE

The squares divide the number line into many segments with different heights.
Therefore we can use a TreeMap to store the number line.
The key is the starting point of each segment and the value is the height of the segment.
For every new falling square (s, l), we update those segments between s and s + l.



TreeMap Soln:

class Solution {
    public List<Integer> fallingSquares(int[][] positions) {
        List<Integer> list = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // at first, there is only one segment starting from 0 with height 0
        map.put(0, 0);

        // The global max height is 0
        int max = 0;

        for(int[] position : positions) {

            // the new segment
            int start = position[0], end = start + position[1];

            // find the height among this range
            Integer key = map.floorKey(start);
            int h = map.get(key);
            key = map.higherKey(key);
            while(key != null && key < end) {
                h = Math.max(h, map.get(key));
                key = map.higherKey(key);
            }
            h += position[1];

            // update global max height
            max = Math.max(max, h);
            list.add(max);

            // update new segment and delete previous segments among the range
            int tail = map.floorEntry(end).getValue();
            map.put(start, h);
            map.put(end, tail);
            key = map.higherKey(start);
            while(key != null && key < end) {
                map.remove(key);
                key = map.higherKey(key);
            }
        }
        return list;
    }
}

Perform Coordinate Compression:

 public List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        Map<Integer, Integer> cc = coorCompression(positions);
        int best = 0;
        List<Integer> res = new ArrayList<>();
        SegmentTree tree = new SegmentTree(cc.size());
        for (int[] pos : positions) {
            int L = cc.get(pos[0]);
            int R = cc.get(pos[0] + pos[1] - 1);
            int h = tree.query(L, R) + pos[1];
            tree.update(L, R, h);
            best = Math.max(best, h);
            res.add(best);
        }
        return res;
    }

    private Map<Integer, Integer> coorCompression(int[][] positions) {
        Set<Integer> set = new HashSet<>();
        for (int[] pos : positions) {
            set.add(pos[0]);
            set.add(pos[0] + pos[1] - 1);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        Map<Integer, Integer> map = new HashMap<>();
        int t = 0;
        for (int pos : list) map.put(pos, t++);
        return map;
    }

Coordinate compression is a procedure that takes some points and reassigns their coordinates to remove "gaps".
For example, if point P1 is located at x = 5, point P2 is located at x = 27, and point P3 is located at x = 65,
then, after coordinate compression, P1 may be located at x = 0, P2 may be located at x = 1, and P3 may be located at x = 2.
The reason we compress coordinates is to get rid of all the "empty space" between points. This makes it easier to, say,
use the coordinates as indices into an array. If we used the original numbers, we'd waste a lot of entries.


 */