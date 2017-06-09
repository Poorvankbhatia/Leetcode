/*

You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.

The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis pairs
that don't affect the one-to-one mapping relationship between the string and the original binary tree.

Example 1:
Input: Binary tree: [1,2,3,4]
       1
     /   \
    2     3
   /
  4

Output: "1(2(4))(3)"

Explanation: Originally it needs to be "1(2(4)())(3()())",
but you need to omit all the unnecessary empty parenthesis pairs.
And it will be "1(2(4))(3)".
Example 2:
Input: Binary tree: [1,2,3,null,4]
       1
     /   \
    2     3
     \
      4

Output: "1(2()(4))(3)"

Explanation: Almost the same as the first example,
except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.

 */
package tree.easy;

import tree.TreeNode;

/**
 * Created by poorvank on 04/06/17.
 */
public class ConstructString {

    public String tree2str(TreeNode t) {

        if(t==null) {
            return "";
        }

        StringBuilder result = new StringBuilder(convertUtil(t));
        result.deleteCharAt(0);
        result.deleteCharAt(result.length()-1);

        return result.toString();

    }

    private String convertUtil(TreeNode t) {
        if(t==null) {
            return "";
        }

        if(t.left==null && t.right==null) {
            return "("+t.val+")";
        }

        String convertedLeft = convertUtil(t.left);
        String convertedRight = convertUtil(t.right);

        if(convertedLeft.length()==0 && convertedRight.length()!=0) {
            return "("+t.val+"()"+convertedRight+")";
        } else {
            return "("+t.val+convertedLeft+convertedRight+")";
        }
    }

    public static void main(String[] args) {
        TreeNode t = new TreeNode(1);
        t.right = new TreeNode(3);
        t.right.right=new TreeNode(5);
        System.out.println(new ConstructString().tree2str(t));
    }

}
