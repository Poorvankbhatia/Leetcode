/*
Given a binary tree where each path going from the root to any leaf form a valid sequence,
check if a given string is a valid sequence in such binary tree.

We get the given string from the concatenation of an array of integers arr and the concatenation of all
values of the nodes along a path results in a sequence in the given binary tree.

Example 1:
Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,0,1]
Output: true
Explanation:
The path 0 -> 1 -> 0 -> 1 is a valid sequence (green color in the figure).
Other valid sequences are:
0 -> 1 -> 1 -> 0
0 -> 0 -> 0

Example 2
Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,0,1]
Output: false
Explanation: The path 0 -> 0 -> 1 does not exist, therefore it is not even a sequence.


Input: root = [0,1,0,0,1,0,null,null,1,0,0], arr = [0,1,1]
Output: false
Explanation: The path 0 -> 1 -> 1 is a sequence, but it is not a valid sequence.


Constraints:

1 <= arr.length <= 5000
0 <= arr[i] <= 9
Each node's value is between [0 - 9].
 */
package tree.medium;

import tree.TreeNode;

public class IsValidSequence {

    public boolean isValidSequence(TreeNode root, int[] arr) {
        if (root==null) {
            return false;
        }
        return util(root,0,arr);
    }

    private boolean util(TreeNode root,int index,int[] arr) {
        //If last element is equal to the leaf value.
        if(index==arr.length-1 && (root!=null && root.left==null && root.right==null)) {
            return arr[index]==root.val;
        }
        // Either root is null or index has been crossed or value doesn't match
        if(root==null || index>=arr.length || root.val!=arr[index]) {
            return false;
        }
        // Check left and right.
        return util(root.left,index+1,arr) || util(root.right,index+1,arr);
    }

}

/*

Also check Linked BT.


 */