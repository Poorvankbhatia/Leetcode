/*
Given a binary tree where node values are digits from 1 to 9. A path in the binary tree is said to be pseudo-palindromic
if at least one permutation of the node values in the path is a palindrome.

Return the number of pseudo-palindromic paths going from the root node to leaf nodes.

Input: root = [2,3,1,3,1,null,1]
Output: 2
Explanation: The figure above represents the given binary tree. There are three paths going from the root node to leaf nodes:
the red path [2,3,3], the green path [2,1,1], and the path [2,3,1]. Among these paths only red path and green path are
pseudo-palindromic paths since the red path [2,3,3] can be rearranged in [3,2,3] (palindrome) and the green path [2,1,1] can be rearranged in [1,2,1] (palindrome).
 */
package tree.medium;

import tree.TreeNode;

public class PseudoPalindromicPaths {

    int ans;
    public int pseudoPalindromicPaths (TreeNode root) {
        ans = 0;
        util(root,new int[10]);
        return ans;
    }

    private void util(TreeNode root,int[] count) {
        if(root!=null) {
            count[root.val]++;
            if(root.left==null && root.right==null) {
                int oddCount=0;
                for (int n : count) {
                    if((n%2)!=0) {
                        oddCount++;
                    }
                }
                ans = (oddCount==0 || oddCount==1)?ans+1:ans;
            } else {
                if(root.left!=null) {
                    util(root.left,count);
                }
                if(root.right!=null) {
                    util(root.right,count);
                }
            }
            count[root.val]--;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(1);
        System.out.println(new PseudoPalindromicPaths().pseudoPalindromicPaths(root));
    }

}

/*

Bitmask method:

Whenever meet an element,
toggle the corresponding bit using ^ operation.

At the leaf node,
check if the count has only one bit that is 1.

We use lowbit to help count this.
Time O(N)
Space O(K + H)

 public int pseudoPalindromicPaths (TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int count) {
        if (root == null) return 0;
        count ^= 1 << (root.val - 1);
        int res = dfs(root.left, count) + dfs(root.right, count);
        if (root.left == root.right && (count & (count - 1)) == 0) res++;
        return res;
    }

 */