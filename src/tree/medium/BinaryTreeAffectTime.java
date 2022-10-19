/*

You are given the root of a binary tree with unique values, and an integer start.
At minute 0, an infection starts from the node with value start.

Each minute, a node becomes infected if:

The node is currently uninfected.
The node is adjacent to an infected node.
Return the number of minutes needed for the entire tree to be infected.

Input: root = [1,5,3,null,4,10,6,9,2], start = 3
Output: 4
Explanation: The following nodes are infected during:
- Minute 0: Node 3
- Minute 1: Nodes 1, 10 and 6
- Minute 2: Node 5
- Minute 3: Node 4
- Minute 4: Nodes 9 and 2
It takes 4 minutes for the whole tree to be infected so we return 4.

Input: root = [1], start = 1
Output: 0
Explanation: At minute 0, the only node in the tree is infected so we return 0.


Constraints:

The number of nodes in the tree is in the range [1, 105].
1 <= Node.val <= 105
Each node has a unique value.
A node with a value of start exists in the tree.

 */

package tree.medium;

import tree.TreeNode;

import java.util.*;

public class BinaryTreeAffectTime {

    public int amountOfTime(TreeNode root, int start) {

        Queue<TreeNode> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        queue.add(root);
        TreeNode startNode = null;
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if(poll.val==start) {
                startNode = poll;
            }
            map.put(poll.val, map.getOrDefault(poll.val, new ArrayList<>()));
            if(poll.left != null) {
                map.get(poll.val).add(poll.left.val);
                map.put(poll.left.val, map.getOrDefault(poll.left.val, new ArrayList<>()));
                map.get(poll.left.val).add(poll.val);
                queue.add(poll.left);
            }
            if(poll.right!=null) {
                map.get(poll.val).add(poll.right.val);
                map.put(poll.right.val, map.getOrDefault(poll.right.val, new ArrayList<>()));
                map.get(poll.right.val).add(poll.val);
                queue.add(poll.right);
            }
        }

        return calculatedTime(startNode,map);

    }

    private int calculatedTime(TreeNode start, Map<Integer,List<Integer>> map) {

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(start.val);
        int time = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                Integer poll = queue.poll();
                visited.add(poll);
                for (Integer nod : map.get(poll)) {
                    if(visited.add(nod)) {
                        queue.add(nod);
                    }
                }
            }
            time++;
        }
        return time-1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.right = new TreeNode(3);
        System.out.println(new BinaryTreeAffectTime().amountOfTime(root,3));
    }

}
