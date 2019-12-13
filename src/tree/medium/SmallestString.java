/*

Given the root of a binary tree, each node has a value from 0 to 25 representing the letters 'a' to 'z': a value of 0 represents 'a',
 a value of 1 represents 'b', and so on.

Find the lexicographically smallest string that starts at a leaf of this tree and ends at the root.

(As a reminder, any shorter prefix of a string is lexicographically smaller: for example, "ab" is lexicographically smaller than "aba".
A leaf of a node is a node that has no children.)

Input: [0,1,2,3,4,3,4]
Output: "dba"


Input: [0,1,18,2,19,null,21,3,5,null,null,null,7,7,4,8,6,null,null,19,25,11,3,10,14,9,13,null,24,null,22,null,null,
1,null,6,20,17,15,12,13,15,null,null,null,10,2,null,6,null,7,22,19,null,null,16,23,null,null,null,15,0,20,7,8,13,null,
null,null,6,null,25,null,23,4,10,23,null,null,null,7,null,13,null,null,null,14,16,13,10,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,16,null,null,10,null,20]
Output: "etukifcba"

 */

package tree.medium;

import tree.TreeNode;

public class SmallestString {

    public String smallestFromLeaf(TreeNode root) {
        return dfs(root,"");
    }

    private String dfs(TreeNode root,String suffix) {
        if(root==null) {
            return suffix;
        }
        String current = (char)(root.val+'a')+suffix;
        if(root.left==null) {
            return dfs(root.right,current);
        } else if(root.right==null) {
            return dfs(root.left,current);
        } else {
            String left = dfs(root.left,current);
            String right = dfs(root.right,current);
            return left.compareTo(right)>=0?right:left;
        }
    }

}

/*
time complexity would be O(NlogN).

Case :  [4,0,1,1]
        e
       / \
      a  b
     /
    b

    Another:

       z
      /
     b
    / \
   a  a
  /
 b
 /
a
 */
