package tree.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by poorvank.b on 04/01/19.
 */
public class NArrayTreeLevelOrder {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size=queue.size();
            List<Integer> list = new ArrayList<>();
            while(size>0) {
                Node current = queue.poll();
                list.add(current.val);
                for(int i=0;i<current.children.size();i++) {
                    queue.add(current.children.get(i));
                }
                size--;
            }
            result.add(list);
        }
        return result;
    }
}
