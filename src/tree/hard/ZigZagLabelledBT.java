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
        while (parent!=1) {
            int level = (int)(Math.log(parent)/Math.log(2))+1;
            int maxAtLevel = (int) Math.pow(2,level)-1;
            int minAtLevel = (int) Math.pow(2,level-1);
            parent = (maxAtLevel-parent+minAtLevel)/2;
            result.addFirst(parent);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ZigZagLabelledBT().pathInZigZagTree(14));
    }

}

/*

Step by Step Explanation
With the thought in mind that in an ordered binary tree that goes from 1 to n:

Normally Ordered Binary Tree:
             1
           /   \
         2       3
       /  \     /  \
     4     5   6     7
   / |    /|   |\    | \
 8   9  10 11 12 13  14  15
Thought 1) You can easily determine the parent by dividing by 2 with a normally ordered (non-zigzag) binary tree
For example the parent of 9 can be calculated via int(9/2) which is 4

Thought 2) So we now how how to trace from the input label to the root node. So lets start with label
In our example, we will use 14. To determine the parent of 14, notice that in the same spot in a normally
ordered binary tree that it is 9. So you just need to calculate how to get from 14 to 9.

Zig Zag Binary Tree:
             1
           /   \
         3       2  <- 3+2-3 = 2/2 = 1
       /  \     /  \
     4     5   6     7   <- 7+4-4 = 7/2 = 3
   / |    /|   |\    | \
 15 14  13 12 11 10  9  8   <- 15+8-14 = 9/2 = 4
inversion formula: (max number of current level + min number of current level) - current number
For example to find the inversion of 14: 15 + 8 - 14 = 9
From here you just divide 9 by 2 to find the parent 4

Thought 3) You have to run the inversion formula at every level because at every level the row is inverted relative to the previous row

Time Complexity:
O(3 log n). 3 are needed as commented in the code.

Space Complexity:
If including the space required for the return res object counts as space then we need
O(log n) because we need to store the path from the root to the label.

 */