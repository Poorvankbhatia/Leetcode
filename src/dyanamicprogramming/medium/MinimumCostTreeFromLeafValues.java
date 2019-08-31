/*

Given an array arr of positive integers, consider all binary trees such that:

Each node has either 0 or 2 children;
The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.
It is guaranteed this sum fits into a 32-bit integer.



Example 1:

Input: arr = [6,2,4]
Output: 32
Explanation:
There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

    24            24
   /  \          /  \
  12   4        6    8
 /  \               / \
6    2             2   4


Constraints:

2 <= arr.length <= 40
1 <= arr[i] <= 15
It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).

 */
package dyanamicprogramming.medium;

import java.util.HashMap;
import java.util.Map;

public class MinimumCostTreeFromLeafValues {

    private Map<String, Integer> resultMap; // Stores result of minimum value for a given index range
    private Map<String, Integer> highestValMap;// Store highest leaf node for a given index range.

    public int mctFromLeafValues(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int start = 0;
        int end = arr.length - 1;
        resultMap = new HashMap<>();
        highestValMap = new HashMap<>();

        return util(start, end, arr);
    }

    private int util(int start, int end, int[] arr) {
        String key = start + "_" + end; // Key of the map : Comprising of start & end index.
        if (resultMap.containsKey(key)) {
            return resultMap.get(key);
        } else if (start == end) {
            int value = 0;
            resultMap.put(key, value);
            highestValMap.put(key, arr[start]);
            return value;
        } else if (start + 1 == end) {
            int value = arr[start] * arr[end];
            resultMap.put(key, value);
            highestValMap.put(key, Math.max(arr[start], arr[end]));
            return value;
        }
        int minValue = Integer.MAX_VALUE;
        int i = 0;
        /**
         * Lets say the size of the array is 5[0,4]: To find minimum result , Group array as
         * (0,0) (1,4)
         * (0,1) (2,4)
         * (0,2) (3,4)
         * (0,3) (4,4)
         */
        while (i < end - start) {
            int left = util(start, start + i, arr);
            int leftHighest = highestValMap.get(start + "_" + (start + i));
            int right = util(start + i + 1, end, arr);
            int rightHighest = highestValMap.get(((start + i + 1) + "_" + end));
            int val = (left + right) + (leftHighest * rightHighest);
            if (val < minValue) {
                minValue = val;
                resultMap.put(key, minValue);
                highestValMap.put(key, Math.max(rightHighest, leftHighest));
            }
            i++;
        }

        return minValue;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{6, 2, 4, 9, 1, 5};
        System.out.println(new MinimumCostTreeFromLeafValues().mctFromLeafValues(arr));
    }

}

/*

Stack solution:

When we build a node in the tree, we compared the two numbers a and b.
In this process,
the smaller one is removed and we won't use it anymore,
and the bigger one actually stays.

The problem can translated as following:
Given an array A, choose two neighbors in the array a and b,
we can remove the smaller one min(a,b) and the cost is a * b.
What is the minimum cost to remove the whole array until only one left?

To remove a number a, it needs a cost a * b, where b >= a.
So a has to be removed by a bigger number.
We want minimize this cost, so we need to minimize b.

b has two candidates, the first bigger number on the left,
the first bigger number on the right.

The cost to remove a is a * min(left, right)

public int mctFromLeafValues(int[] A) {
        int res = 0, n = A.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(Integer.MAX_VALUE);
        for (int a : A) {
            while (stack.peek() <= a) {
                int mid = stack.pop();
                res += mid * Math.min(stack.peek(), a);
            }
            stack.push(a);
        }
        while (stack.size() > 2) {
            res += stack.pop() * stack.peek();
        }
        return res;
    }

 O(N) : The problem can be translated into removing all local minium values while finding the first greater element on the left and on the right.

 */