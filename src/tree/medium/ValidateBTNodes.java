/*
You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i],
return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.

Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
Output: true

Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
Output: false

Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
Output: false

Input: n = 6, leftChild = [1,-1,-1,4,-1,-1], rightChild = [2,-1,-1,5,-1,-1]
Output: false


Constraints:

1 <= n <= 10^4
leftChild.length == rightChild.length == n
-1 <= leftChild[i], rightChild[i] <= n - 1
 */
package tree.medium;

import java.util.Arrays;

public class ValidateBTNodes {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {

        int[] parent = new int[n];
        Arrays.fill(parent,-1);

        for (int i=0;i<n;i++) {
            if(leftChild[i]!=-1) {
                // If parent already assigned.
                if(parent[leftChild[i]]!=-1) {
                    return false;
                }
                parent[leftChild[i]]=i;
            }
            if(rightChild[i]!=-1) {
                // If parent already assigned.
                if(parent[rightChild[i]]!=-1) {
                    return false;
                }
                parent[rightChild[i]]=i;
            }
        }

        int rootCount = 0;
        // If 2 roots or none of them is a root.
        for (int x : parent) {
            if(x==-1) {
                rootCount++;
            }
        }

        return rootCount==1;
    }

}
