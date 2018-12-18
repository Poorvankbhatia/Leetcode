/*

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

3
/ \
9  20
/  \
15   7

*/
package tree.medium;

import tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 18/12/18.
 */
public class BuildTreeInPre {




        public TreeNode buildTree(int[] preorder, int[] inorder) {

            Map<Integer,Integer> indexMap = new HashMap<>();

            for(int i=0;i<inorder.length;i++) {
                indexMap.put(inorder[i],i);
            }

            return util(0,preorder.length-1,preorder,0,inorder.length-1,indexMap);

        }

        private TreeNode util(int preStart, int preEnd, int[] preorder, int inStart, int inEnd, Map<Integer,Integer> map) {

            if(preStart>preEnd || inStart>inEnd) {
                return null;
            }

            int val = preorder[preStart];
            int index = map.get(val);
            int leftCount = index-inStart;

            TreeNode root = new TreeNode(val);

            root.left = util(preStart+1,preStart+leftCount,preorder,inStart,index-1,map);
            root.right = util(preStart+leftCount+1,preEnd,preorder,index+1,inEnd,map);

            return root;

        }

}
