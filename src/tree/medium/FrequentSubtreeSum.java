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

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by poorvank on 08/02/17.
 */
public class FrequentSubtreeSum {

    private int maxFrequency=0;

    public int[] findFrequentTreeSum(TreeNode root) {

        HashMap<Integer,Integer> map = new HashMap<>();
        frequentSumUtil(root,map);

        int size = map.size();
        for (Integer key : map.keySet()) {
            if(map.get(key)!=maxFrequency) {
                size--;
            }
        }

        int[] result = new int[size];
        int i=0;
        for (Integer key : map.keySet()) {
            if(map.get(key)==maxFrequency) {
                result[i] = key;
                i++;
            }
        }

        return result;

    }

    private int frequentSumUtil(TreeNode root,HashMap<Integer,Integer> map) {

        if(root==null) {
            return 0;
        }

        if(root.right==null && root.left==null) {
            fillMap(map,root.val);
            return root.val;
        }

        int rootVal = root.val;
        int left = frequentSumUtil(root.left,map);
        int right = frequentSumUtil(root.right,map);

        int total = rootVal+left+right;

        fillMap(map,total);


        return total;

    }

    private void fillMap(HashMap<Integer,Integer> map,int val) {
        if(map.containsKey(val)) {
            map.put(val,map.get(val)+1);
        } else {
            map.put(val,1);
        }

        if(map.get(val)>maxFrequency) {
            maxFrequency = map.get(val);
        }
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(5);
        node.left=new TreeNode(2);
        node.right = new TreeNode(-3);

        System.out.print(Arrays.toString(new FrequentSubtreeSum().findFrequentTreeSum(node)));

    }

}
