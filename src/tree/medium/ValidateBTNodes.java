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
import java.util.HashSet;
import java.util.Set;

public class ValidateBTNodes {

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {

        Set<Integer>[] childArr = new Set[n];
        int[] parent = new int[n];
        Arrays.fill(parent,-1);
        for (int i=0;i<n;i++) {
            childArr[i]= new HashSet<>();
        }

        for (int i=0;i<n;i++) {
            if(leftChild[i]!=-1) {
                // If parent already assigned or leftChild[i] is already a grand parent of i;
                if(parent[leftChild[i]]!=-1 || isCurrentChildAPreviousParent(leftChild[i],childArr,i)) {
                    return false;
                }
                parent[leftChild[i]]=i;
                childArr[i].add(leftChild[i]);
            }
            if(rightChild[i]!=-1) {
                // If parent already assigned or rightChild[i] is already a grand parent of i;
                if(parent[rightChild[i]]!=-1 || isCurrentChildAPreviousParent(rightChild[i],childArr,i)) {
                    return false;
                }
                parent[rightChild[i]]=i;
                childArr[i].add(rightChild[i]);
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

    // Check if the child is a grand parent of the current index
    private boolean isCurrentChildAPreviousParent(int child, Set<Integer>[] childArr, int parent) {
        if(child==parent) {
            return true;
        }
        for (Integer c : childArr[child]) {
            boolean v =  isCurrentChildAPreviousParent(c,childArr,parent);
            if(v) {
                return v;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new ValidateBTNodes().validateBinaryTreeNodes(4,new int[]{1, 2, 0, -1},new int[]{-1,-1,-1,-1}));
    }

}
