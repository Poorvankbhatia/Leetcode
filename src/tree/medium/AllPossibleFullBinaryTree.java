/*

A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.

 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank.b on 27/08/18.
 */
public class AllPossibleFullBinaryTree {


    Map<Integer, List<TreeNode>> cache = new HashMap<>();

    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N % 2 == 0) {
            return res;
        }
        if (cache.containsKey(N)) {
            return cache.get(N);
        }
        if (N == 1) {
            res.add(new TreeNode(0));
            return res;
        }

        N = N - 1;
        for (int i = 1; i < N; i += 2) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N - i);
            for (TreeNode nl : left) {
                for (TreeNode nr : right) {
                    TreeNode cur = new TreeNode(0);
                    cur.left = nl;
                    cur.right = nr;
                    res.add(cur);
                }
            }
        }
        cache.put(N, res);
        return res;
    }

}
