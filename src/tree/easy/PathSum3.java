/*

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

 */
package tree.easy;

import tree.TreeNode;


/**
 * Created by poorvank.b on 07/11/16.
 */
public class PathSum3 {

    private int count = 0;

    public int pathSum(TreeNode root, int sum) {

        if(root!=null) {
            pathSumUtil(root,sum);
            pathSum(root.left,sum);
            pathSum(root.right,sum);
        }

        return count;
    }

    public void pathSumUtil(TreeNode root,int sum) {

        if(root!=null) {

            if(sum-root.val==0) {
                count++;
            }

            pathSumUtil(root.right,sum-root.val);
            pathSumUtil(root.left,sum-root.val);

        }
    }

    public static void main(String[] args) {

        TreeNode node = new TreeNode(10);
        node.right = new TreeNode(-3);
        node.right.right = new TreeNode(11);
        node.left = new TreeNode(5);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(2);
        node.left.right.right = new TreeNode(1);
        node.left.left.right = new TreeNode(-2);
        node.left.left.left = new TreeNode(3);
        System.out.println(new PathSum3().pathSum(node,8));

    }


}

//Calculate count of paths for every node.

/*

Another sol:

public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        return findPathSum(root, 0, sum, map);
    }
    private int findPathSum(TreeNode curr, int sum, int target, Map<Integer, Integer> map) {
        if (curr == null) {
            return 0;
        }
        // update the prefix sum by adding the current val
        sum += curr.val;
        // get the number of valid path, ended by the current node
        int numPathToCurr = map.getOrDefault(sum-target, 0);
        // update the map with the current sum, so the map is good to be passed to the next recursion
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        // add the 3 parts discussed in 8. together
        int res = numPathToCurr + findPathSum(curr.left, sum, target, map)
                                               + findPathSum(curr.right, sum, target, map);
       // restore the map, as the recursion goes from the bottom to the top
        map.put(sum, map.get(sum) - 1);
        return res;
    }

 */
