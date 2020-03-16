/*

Given two binary trees original and cloned and given a reference to a node target in the original tree.

The cloned tree is a copy of the original tree.

Return a reference to the same node in the cloned tree.

Note that you are not allowed to change any of the two trees or the target node and the answer must be a reference to a node in the cloned tree.

Follow up: Solve the problem if repeated values on the tree are allowed.

Input: tree = [7,4,3,null,null,6,19], target = 3
Output: 3
Explanation: In all examples the original and cloned trees are shown. The target node is a green node from the original tree. The answer is the yellow node from the cloned tree.


Input: tree = [1,2,null,3], target = 2
Output: 2


Constraints:

The number of nodes in the tree is in the range [1, 10^4].
The values of the nodes of the tree are unique.
target node is a node from the original tree and is not null.



 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FindCloneNode {

    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        List<TreeNode> l1 = new ArrayList<>();
        List<TreeNode> l2 = new ArrayList<>();
        getArr(original,l1);
        getArr(cloned,l2);
        int i;
        for (i=0;i<l1.size();i++) {
            if(l1.get(i).equals(target)) {
                break;
            }
        }
        return l2.get(i);
    }

    private void getArr(TreeNode root, List<TreeNode> list) {
        if(root!=null) {
            getArr(root.left,list);
            list.add(root);
            getArr(root.right,list);
        }
    }

}
