/*

In an infinite binary tree where every node has two children, the nodes are labelled in row order.

In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right,
while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.

Given the label of a node in this tree, return the labels in the path from the root of the tree to the node with that label.



Example 1:

Input: label = 14
Output: [1,3,4,14]
Example 2:

Input: label = 26
Output: [1,2,6,10,26]


Constraints:

1 <= label <= 10^6


 */
package tree.hard;

import java.util.LinkedList;
import java.util.List;

public class ZigZagLabelledBT {

    public List<Integer> pathInZigZagTree(int label) {
        LinkedList<Integer> result = new LinkedList<>();
        int parent = label;
        result.addFirst(parent);

        while(parent != 1) {
            int d = (int)(Math.log(parent)/Math.log(2));
            int offset = (int)Math.pow(2, d+1) - 1 - parent;
            parent = ((int)Math.pow(2, d) + offset) / 2;
            result.addFirst(parent);
        }

        return result;
    }

}

/*

Calculate current depth of the label
Calculate offset (for each depth, values lie from 2^d -> 2^(d+1) -1
Find the real parent based on offset
Repeat until we reach 1
e.g. parent of 14 is 4

depth = 3, values in this depth lie from 8 to 15 (since it is a complete binary tree)
offset = 15 - 14 = 1
real parent of 14 = parent of ( 8 + offset ) = parent (9) = 9/2 = 4

 */