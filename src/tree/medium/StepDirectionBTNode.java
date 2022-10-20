/*
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n.
You are also given an integer startValue representing the value of the start node s, and a different integer
destValue representing the value of the destination node t.

Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such
path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:

'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.

Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.

Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.


Constraints:

The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue
 */

package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class StepDirectionBTNode {

    public String getDirections(TreeNode root, int startValue, int destValue) {

        TreeNode lca = lowestCommonAncestor(root,startValue,destValue);
        List<Character> startList = new ArrayList<>();
        direction(lca, startValue, startList);
        List<Character> endList = new ArrayList<>();
        direction(lca, destValue, endList);
        StringBuilder sb = new StringBuilder();
        sb.append("U".repeat(startList.size()));
        for (Character s : endList) sb.append(s);
        return sb.toString();
    }


    private boolean direction(TreeNode lca, int value, List<Character> characters) {
        if(lca == null) return false;
        if (lca.val == value) return true;
        characters.add('L');
        if (direction(lca.left, value, characters)) return true;
        characters.remove(characters.size() - 1);
        characters.add('R');
        if (direction(lca.right, value, characters)) return true;
        characters.remove(characters.size() - 1);
        return false;
    }



    private TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if(root==null || root.val==p || root.val==q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left,p,q);
        TreeNode right = lowestCommonAncestor(root.right,p,q);
        if(left==null) {
            return right;
        }else if(right==null) {
            return left;
        } else {
            return root;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        System.out.println(new StepDirectionBTNode().getDirections(root, 1, 2));
    }


}

/*

get LCA
find the path from ancestor to start / dest

BFS like closestLeaf will cause TLE for large test cases.
 */
