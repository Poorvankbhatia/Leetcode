package tree.easy;

import java.util.List;

/**
 * Created by poorvank.b on 04/01/19.
 */
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
