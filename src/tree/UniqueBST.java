/*

Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

 */

package tree;

/**
 * Created by poorvank on 24/09/16.
 */
public class UniqueBST {

    public int numTrees(int n) {

        int[] result = new int[n+1];

        if(n==0 || n==1) {
            return 1;
        }

        result[0]=result[1]=1;

        for (int i=2;i<=n;i++) {
            result[i] = 0;

            for (int j=1;j<=i;j++) {
                result[i] += (result[i-j]*result[j-1]);
            }
        }

        return result[n];

    }


}


/*

 Consider all possible binary search trees with each element at the root. If there are n nodes,
 then for each choice of root node, there are n – 1 non-root nodes and these non-root nodes must be partitioned
 into those that are less than a chosen root and those that are greater than the chosen root.

Let’s say node i is chosen to be the root. Then there are i – 1 nodes smaller than i and n – i nodes bigger than i.
For each of these two sets of nodes, there is a certain number of possible subtrees.

Let t(n) be the total number of BSTs with n nodes. The total number of BSTs with i at the root is t(i – 1) t(n – i).
The two terms are multiplied together because the arrangements in the left and right subtrees are independent.
That is, for each arrangement in the left tree and for each arrangement in the right tree, you get one BST with i at the root.

Summing over i gives the total number of binary search trees with n nodes.

t(n) = (from i=1 to n)(Sum(t(i-1)*t(n-i));


 */