/*

Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.

For example,
Given n = 3, your program should return all 5 unique BST's shown below.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 07/04/17.
 */
public class UniqueBST2 {

    public List<TreeNode> generateTrees(int n) {
        if(n==0) {
            return new ArrayList<>();
        }
        return generateUtil(1,n);
    }

    private List<TreeNode> generateUtil(int start,int end) {

        List<TreeNode> list = new ArrayList<>();

        if(start>end) {
            list.add(null);
            return list;
        }

        for (int i=start;i<=end;i++) {
            List<TreeNode> left = generateUtil(start,i-1);
            List<TreeNode> right = generateUtil(i+1,end);

            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode node = new TreeNode(i);
                    node.left = l;
                    node.right = r;
                    list.add(node);
                }

            }

        }


        return list;
    }

    public static void main(String[] args) {
        new UniqueBST2().generateTrees(3);
    }

}
