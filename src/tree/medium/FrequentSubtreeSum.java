/*

Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the
sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent
subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.

 */
package tree.medium;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 08/02/17.
 */
public class FrequentSubtreeSum {

    private int max=Integer.MIN_VALUE;
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer,Integer> map = new HashMap<>();
        int[] result;
        if(root==null) {
            return new int[0];
        }
        sum(root,map);
        ArrayList<Integer> list = new ArrayList<>();
        int size=0;
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            if(entry.getValue()==max) {
                list.add(entry.getKey());
                size++;
            }
        }
        result = new int[size];
        for(int i=0;i<size;i++) {
            result[i]=list.get(i);
        }
        return result;
    }

    private int sum(TreeNode root,Map<Integer,Integer> map) {

        if(root==null) {
            return 0;
        }

        int left = sum(root.left,map);
        int right = sum(root.right,map);

        int sum = root.val+left+right;
        map.put(sum,map.getOrDefault(sum,0)+1);
        max = Math.max(max,map.get(sum));
        return sum;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left=new TreeNode(2);
        node.right = new TreeNode(-3);

        System.out.print(Arrays.toString(new FrequentSubtreeSum().findFrequentTreeSum(node)));

    }

}
