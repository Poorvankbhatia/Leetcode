/*

We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.



Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation:
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.


Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.

 */
package tree.medium;

import tree.TreeNode;

import java.util.*;

/**
 * Created by poorvank.b on 08/07/18.
 */
public class AllNodesAtDistanceK {

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

        Map<TreeNode,TreeNode> parentMap = new HashMap<>();
        fillParentMap(root,parentMap,root);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        Set<Integer> visited = new HashSet<>();
        visited.add(target.val);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            if(K==0) {
                for (int i = 0; i < size ; i++)  {
                    result.add(queue.poll().val);
                }
                break;
            }

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current.left != null && visited.add(current.left.val)) {
                    queue.add(current.left);
                }
                if (current.right != null && visited.add(current.right.val)) {
                    queue.add(current.right);
                }
                if (parentMap.containsKey(current) && visited.add(parentMap.get(current).val)) {
                    queue.add(parentMap.get(current));
                }
            }

            K--;
        }

        return result;

    }

    private void fillParentMap(TreeNode root,Map<TreeNode,TreeNode> parentMap,TreeNode parent) {
        if(root!=null){
            parentMap.put(root,parent);
            fillParentMap(root.left,parentMap,root);
            fillParentMap(root.right,parentMap,root);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(2);
        root.right=new TreeNode(1);
        root.right.left = new TreeNode(3);
        System.out.println(new AllNodesAtDistanceK().distanceK(root,root.right.left,3));

    }

}


/*

O(N), where N is the number of nodes in the given tree.

Without using map:

Intially, we propagate a val = 0 down the tree until target is found.
Once we have the target we start incrementing the val and then propagate,
when we have val = k, we can add that node to result.
Later, we return val = 0 from the children if it's not the target node and it doesn't
have the target in the subtree. If we find the target we return val=1. when a node receives a
returned val > 0, it needs to re-propagate the new val to the other subtree and return val+1.

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> dist = new ArrayList<Integer>();
				// Note : Since target is always in the tree, we can do this.
        if( K == 0){
            dist.add(target.val);
            return dist;
        }
        traverse(dist, root, target, K, 0);
        return dist;
    }
    public int traverse(List<Integer> dist, TreeNode root, TreeNode target, int k, int val){
        if(root == null)
            return 0;
						// val == k implies that we are at the k-th distant child from the target
        if(val == k){
            dist.add(root.val);
            return 0;
        }
        int left = 0, right = 0;

				// either we find the target or we have already found the target propagate val+1
        if(root.val == target.val || val > 0 ){
            left = traverse(dist, root.left, target, k, val+1);
            right = traverse(dist, root.right, target, k, val+1);
        }

				// else propagate the 0
        else {
            left = traverse(dist, root.left, target, k, val);
            right = traverse(dist, root.right, target, k, val);
        }

				// If a node receives a k in return it is k-th distant parent from the the target.
        if(left == k || right == k){
            dist.add(root.val);
            return 0;
        }

				// Target node must return 1 other must return 0 if their subtree doesnt have target.
        if(root.val == target.val){
            return 1;
        }

				// If left or right is greater than 0 that means one of the subtree has the target. Propagate the new value in the other subtree.
        if(left > 0){
            traverse(dist, root.right, target, k, left + 1);
        }
        if(right > 0){
            traverse(dist, root.left, target, k, right+1);
        }
				// If the subtree has target, return the distance from target +1
        if(left > 0 || right > 0)
            return left > 0 ? left + 1 : right + 1;

				// else return 0.
        return 0;
    }
}

https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/discuss/143886/Java-O(1)-space-excluding-recursive-stack-space

 */

