package tree;

import java.util.List;

/**
 * Created by poorvank.b on 01/03/18.
 */
public class NArrayTreeNode {

    public int value;
    public List<NArrayTreeNode> children;

    public NArrayTreeNode(int value, List<NArrayTreeNode> list) {
        this.value = value;
        this.children = list;
    }

    public NArrayTreeNode(int value) {
        this(value,null);
    }
}
