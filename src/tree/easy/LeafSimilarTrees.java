/*

Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.

For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

Two binary trees are considered leaf-similar if their leaf value sequence is the same.

Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

 */
package tree.easy;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by poorvank.b on 22/07/18.
 */
public class LeafSimilarTrees {

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<Integer> leafSeq1 = new ArrayList<>();
        getLeafSequence(root1,leafSeq1);

        List<Integer> leafSeq2 = new ArrayList<>();
        getLeafSequence(root2,leafSeq2);

        System.out.println(leafSeq1.toString()+" "+leafSeq2.toString());

        for (int i=0;i<leafSeq1.size();i++) {
            if(!Objects.equals(leafSeq1.get(i), leafSeq2.get(i))) {
                return false;
            }
        }

        return leafSeq1.size()==leafSeq2.size();

    }

    private void getLeafSequence(TreeNode root, List<Integer> leaves) {
        if(root==null) {
            return;
        }
        if(root.left==null && root.right==null) {
            leaves.add(root.val);
            return;
        }
        getLeafSequence(root.left,leaves);
        getLeafSequence(root.right,leaves);
    }

}
