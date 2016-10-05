/*

One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node,
we record the node's value. If it is a null node, we record using a sentinel value such as #.

     _9_
    /   \
   3     2
  / \   / \
 4   1  #  6
/ \ / \   / \
# # # #   # #
For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.

Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree.
 Find an algorithm without reconstructing the tree.

Each comma separated value in the string must be either an integer or a character '#' representing null pointer.

You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".

Example 1:
"9,3,4,#,#,1,#,#,2,#,6,#,#"
Return true

Example 2:
"1,#"
Return false

Example 3:
"9,#,#,1"
Return false

 */

package stack.medium;

import java.util.Stack;

/**
 * Created by poorvank on 05/10/16.
 */
public class VerifyPreorderSerialization {

    public boolean isValidSerialization(String preorder) {
        Stack<String> stack = new Stack<>();

        String[] arr = preorder.split(",");

        for (String anArr : arr) {

            stack.push(anArr);

            while (stack.size() >= 3
                    && stack.get(stack.size() - 1).equals("#")
                    && stack.get(stack.size() - 2).equals("#")
                    && !stack.get(stack.size() - 3).equals("#")) {

                stack.pop();
                stack.pop();
                stack.pop();
                stack.add("#");
            }


        }

        return stack.size() == 1 && stack.get(0).equals("#");

    }

    public static void main(String[] args) {
        System.out.println(new VerifyPreorderSerialization().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

}


/*

We can keep removing the leaf node until there is no one to remove.
 If a sequence is like "4 # #", change it to "#" and continue. We need a stack so that we can record previous removed nodes.



 */