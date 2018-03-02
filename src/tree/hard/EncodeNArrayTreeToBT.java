package tree.hard;

import tree.NArrayTreeNode;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 01/03/18.
 */
public class EncodeNArrayTreeToBT {

    public TreeNode convertToBT(NArrayTreeNode root) {
        return util(root,new ArrayList<>(),0);
    }


    /*
    The strategy follows two rules:
    1. The left child of each node in the binary tree is the next sibling of the node in the N-ary tree.
    2. The right child of each node in the binary tree is the first child of the node in the N-ary tree.
     */

    private TreeNode util(NArrayTreeNode root,List<NArrayTreeNode> siblings,int i) {

        if(root==null) {
            return null;
        }

        System.out.println(root.value);
        TreeNode BTRoot = new TreeNode(root.value);
        if(siblings.size()==0 || i>=siblings.size()) {
            BTRoot.left = null;
        } else {
            BTRoot.left = util(siblings.get(i),siblings,i+1);
        }
        BTRoot.right = root.children!=null?util(root.children.get(0),root.children,1):null    ;

        return BTRoot;

    }

    public static void main(String[] args) {
        NArrayTreeNode node = new NArrayTreeNode(0);
        NArrayTreeNode c1 = new NArrayTreeNode(1);
        NArrayTreeNode c2 = new NArrayTreeNode(2);
        NArrayTreeNode c3 = new NArrayTreeNode(3);
        NArrayTreeNode c4 = new NArrayTreeNode(4);
        NArrayTreeNode c5 = new NArrayTreeNode(5);
        NArrayTreeNode c6 = new NArrayTreeNode(6);
        List<NArrayTreeNode> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);
        node.children = list;
        list = new ArrayList<>();
        list.add(c5);
        list.add(c6);
        c2.children = list;
        TreeNode result = new EncodeNArrayTreeToBT().convertToBT(node);


    }

}

/*

https://leetcode.com/articles/introduction-to-n-ary-trees/

convert a N-ary tree to a binary tree recursively:

1. Deal with its children recursively.
2. Add its left child as the next child of its parent if it has a left child.
3. Add its right child as the first child of the node itself if it has a right child.

 */