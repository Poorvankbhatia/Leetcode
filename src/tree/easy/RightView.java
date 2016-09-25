package tree.easy;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 15/09/16.
 */
public class RightView {

    private static int max;
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();
        max = 0;
        return viewUtil(root,list,1);
    }

    private List<Integer> viewUtil(TreeNode root, List<Integer> list, int level) {

        if(root==null) {
            return list;
        }

        if(max<level) {
            max = level;
            list.add(root.val);
        }

        list = viewUtil(root.right,list,level+1);
        return viewUtil(root.left,list,level+1);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);

        System.out.println(new RightView().rightSideView(root));
    }

}
