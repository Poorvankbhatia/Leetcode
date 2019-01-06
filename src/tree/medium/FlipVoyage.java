package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 06/01/19.
 */
public class FlipVoyage {

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> path = new ArrayList<>();
        if (preorder(root, voyage, 0, path) == -1) {
            List<Integer> result = new ArrayList<>();
            result.add(-1);
            return result;
        }
        return path;
    }
    private int preorder(TreeNode root, int[] voyage, int i, List<Integer> path) {
        if (root == null) {
            return i;
        }
        if (root.val != voyage[i]) {
            return -1;
        }
        int left = preorder(root.left, voyage, i + 1, path);
        if (left != -1) {
            return preorder(root.right, voyage, left, path);
        }
        // need a flip
        path.add(root.val);
        int right = preorder(root.right, voyage, i + 1, path);
        if (right != -1) {
            return preorder(root.left, voyage, right, path);
        }
        return -1;
    }

}
