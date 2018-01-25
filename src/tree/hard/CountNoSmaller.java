/*

You are given an integer array nums and you have to return a new counts array. The counts array has the property where
 counts[i] is the number of smaller elements to the right of nums[i].

Example:

Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
Return the array [2, 1, 1, 0].

 */

package tree.hard;

import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank on 29/05/17.
 */
public class CountNoSmaller {

    public List<Integer> countSmaller(int[] nums) {
        Integer[] result = new Integer[nums.length];

        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(root, nums[i], i, result, 0);
        }

        return Arrays.asList(result);
    }

    private Node insert(Node root, int num, int i, Integer[] result, int preSum) {
        if (root == null) {
            root = new Node(num, 0);
            result[i] = preSum;
            return root;
        } else if (root.val == num) {
            root.duplicate++;
            result[i] = preSum + root.numOfLeftNodes;
            return root;
        } else if (root.val > num) {
            root.numOfLeftNodes++;
            root.left = insert(root.left, num, i, result, preSum);
        } else {
            root.right = insert(root.right, num, i, result, preSum + root.numOfLeftNodes + root.duplicate);
        }

        return root;
    }

    class Node {
        int val;
        int duplicate = 1;
        int numOfLeftNodes;
        Node left, right;

        Node(int val, int numOfLeftNodes) {
            this.val = val;
            this.numOfLeftNodes = numOfLeftNodes;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3,2,2,6,1};
        System.out.println(new CountNoSmaller().countSmaller(arr));
    }

}

/*

Every node will maintain a val sum recording the total of number on it's left bottom side, duplicate counts the duplication.
For example, [3, 2, 2, 6, 1], from back to beginning,we would have:

                1(0, 1)
                     \
                     6(3, 1)
                     /
                   2(0, 2)
                       \
                        3(0, 1)

When we try to insert a number, the total number of smaller number would be adding duplicate and sum of the nodes where we turn right.
for example, if we insert 5, it should be inserted on the way down to the right of 3, the nodes where we turn right is 1(0,1), 2,(0,2), 3(0,1),
so the answer should be (0 + 1)+(0 + 2)+ (0 + 1) = 4

if we insert 7, the right-turning nodes are 1(0,1), 6(3,1), so answer should be (0 + 1) + (3 + 1) = 5

worst time complexity is O(n^2). when processing input like n, n-1, ..., 2, 1.


https://discuss.leetcode.com/topic/31554/11ms-java-solution-using-merge-sort-with-explanation

 */