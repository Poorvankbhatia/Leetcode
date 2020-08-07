/*
You are given a tree with n nodes numbered from 0 to n-1 in the form of a parent array where parent[i]
is the parent of node i. The root of the tree is node 0.

Implement the function getKthAncestor(int node, int k) to return the k-th ancestor of the given node.
If there is no such ancestor, return -1.

The k-th ancestor of a tree node is the k-th node in the path from that node to the root.

Input:
["TreeAncestor","getKthAncestor","getKthAncestor","getKthAncestor"]
[[7,[-1,0,0,1,1,2,2]],[3,1],[5,2],[6,3]]

Output:
[null,1,0,-1]

Explanation:
TreeAncestor treeAncestor = new TreeAncestor(7, [-1, 0, 0, 1, 1, 2, 2]);

treeAncestor.getKthAncestor(3, 1);  // returns 1 which is the parent of 3
treeAncestor.getKthAncestor(5, 2);  // returns 0 which is the grandparent of 5
treeAncestor.getKthAncestor(6, 3);  // returns -1 because there is no such ancestor


Constraints:

1 <= k <= n <= 5*10^4
parent[0] == -1 indicating that 0 is the root node.
0 <= parent[i] < n for all 0 < i < n
0 <= node < n
There will be at most 5*10^4 queries.

 */
package dyanamicprogramming.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeAncestor {

    int[][] moveUp; // For every node we store node present (2^i) levels above it.
    int[] parent; // input parent array
    List<Integer>[] children; // Child to parent mapping
    int logJump; // Maximum Jump required.

    public TreeAncestor(int n, int[] parent) {
        this.logJump = (int)(Math.log(n)/Math.log(2));
        moveUp = new int[n][logJump+1];
        children = new List[n];
        for (int i=0;i<n;i++) {
            Arrays.fill(moveUp[i],-1);
            children[i] = new ArrayList<>();
        }
        // Fill the child array.
        for (int p=0;p<parent.length;p++) {
            if(parent[p]!=-1) {
                children[parent[p]].add(p);
            }
        }
        this.parent = parent;
        fillMemo(0,-1);
    }

    private void fillMemo(int node,int par) {

        // one level up would be the parent.
        moveUp[node][0]=par;
        for (int i=1;i<=logJump;i++) {
            if(moveUp[node][i-1]!=-1) {
                moveUp[node][i] = moveUp[moveUp[node][i-1]][i-1];
            } else {
                moveUp[node][i] = -1;
            }
        }
        for (int child : children[node]) {
            fillMemo(child,node);
        }

    }

    public int getKthAncestor(int node, int k) {
        if(k==0 || node==-1) {
            return node;
        }
        for (int i=logJump;i>=0;i--) {
            // make largest possible jump in the power of two.
            if((1<<i)<=k) {
                return getKthAncestor(moveUp[node][i],k-(1<<i));
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-1,0,0,1,2};
        TreeAncestor ancestor = new TreeAncestor(5, arr);
        System.out.println(ancestor.getKthAncestor(3,5));
        System.out.println(ancestor.getKthAncestor(3,2));


    }

}

/*

used Binary lifting:

Do watch:
https://www.youtube.com/watch?v=FAfSArGC8KY

Also check:
https://www.geeksforgeeks.org/lca-in-a-tree-using-binary-lifting-technique/
 */
