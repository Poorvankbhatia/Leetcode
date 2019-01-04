package tree.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by poorvank.b on 04/01/19.
 */
public class NArrayTreePreOrder {
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if(root==null) {
            return result;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node current = stack.pop();
            result.add(current.val);
            for(int i=current.children.size()-1;i>=0;i--) {
                stack.push(current.children.get(i));
            }
        }
        return result;
    }
}
