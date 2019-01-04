package tree.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Created by poorvank.b on 04/01/19.
 */
public class NArrayPostOrder {

    public List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if(root==null) {
            return result;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            Node current = stack.pop();
            result.add(current.val);
            for(int i=0;i<current.children.size();i++) {
                stack.push(current.children.get(i));
            }
        }
        Collections.reverse(result);
        return result;
    }

}

/*

public List<Integer> postorder(Node root) {
        List<Integer> list = new ArrayList<Integer>();
        if(root == null) return list;

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.add(root);

        while(!stack1.empty())
        {
            Node top = stack1.pop();
            stack2.push(top);
            for(int i = 0; i < top.children.size(); i++)
                stack1.push(top.children.get(i));
        }

        while(!stack2.empty())
            list.add(stack2.pop().val);

        return list;
    }

 */