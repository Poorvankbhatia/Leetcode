/*

A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled,
and all nodes are as far left as possible.

Write a data structure CBTInserter that is initialized with a complete binary tree and supports the following operations:

CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v so that the tree remains complete,
and returns the value of the parent of the inserted TreeNode;
CBTInserter.get_root() will return the head node of the tree.


Example 1:

Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
Output: [null,1,[1,2]]
Example 2:

Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
Output: [null,3,4,[1,2,3,4,5,6,7,8]]


Note:

The initial given tree is complete and contains between 1 and 1000 nodes.
CBTInserter.insert is called at most 10000 times per test case.
Every value of a given or inserted node is between 0 and 5000.

 */
package tree.medium;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank.b on 07/10/18.
 */
//O(1) Insert + O(N) Build Tree:
public class CBTInserter {

    private TreeNode root;
    private Queue<TreeNode> queue;
    //O(N) build tree: Find the first node which doesn't have both left and right child.
    public CBTInserter(TreeNode root) {
        this.root = root;
        queue = new LinkedList<>();
        queue.offer(root);
        while (true) {
            TreeNode cur = queue.peek();
            if (cur.left != null && cur.right != null) {
                queue.offer(cur.left);
                queue.offer(cur.right);
                queue.poll();
            } else {
                break;
            }
        }
    }

    public int insert(int v) {
        TreeNode cur = queue.peek();
        if (cur.left == null) {
            cur.left = new TreeNode(v);
        } else {
            cur.right = new TreeNode(v);
            queue.offer(cur.left);
            queue.offer(cur.right);
            queue.poll();
        }
        return cur.val;
    }

    public TreeNode get_root() {
        return root;
    }

}

/*


O(1) Build Tree + O(N) Insert:
class CBTInserter {
    private TreeNode root;

    public CBTInserter(TreeNode root) {
        this.root = root;
    }

    public int insert(int v) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.offer(cur.left);
            } else {
                cur.left = new TreeNode(v);
                return cur.val;
            }

            if (cur.right != null) {
                queue.offer(cur.right);
            } else {
                cur.right = new TreeNode(v);
                return cur.val;
            }
        }
        return 0;
    }

    public TreeNode get_root() {
        return root;
    }
}

 */