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

 */