/*

Given the array arr of positive integers and the array queries where queries[i] = [Li, Ri],
for each query i compute the XOR of elements from Li to Ri (that is, arr[Li] xor arr[Li+1] xor ... xor arr[Ri] ).
Return an array containing the result for the given queries.


Example 1:

Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
Output: [2,7,14,8]
Explanation:
The binary representation of the elements in the array are:
1 = 0001
3 = 0011
4 = 0100
8 = 1000
The XOR values for queries are:
[0,1] = 1 xor 3 = 2
[1,2] = 3 xor 4 = 7
[0,3] = 1 xor 3 xor 4 xor 8 = 14
[3,3] = 8
Example 2:

Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
Output: [8,0,4,4]


Constraints:

1 <= arr.length <= 3 * 10^4
1 <= arr[i] <= 10^9
1 <= queries.length <= 3 * 10^4
queries[i].length == 2
0 <= queries[i][0] <= queries[i][1] < arr.length

 */
package tree.medium;

import java.util.Arrays;

public class XORQueries {

    private class SegmentNode {
        SegmentNode left;
        SegmentNode right;
        private int[] range;
        private int xor;

        public SegmentNode(int[] range, int xor) {
            this.range = range;
            this.xor = xor;
        }
    }

    SegmentNode root;
    int[] arr;
    public int[] xorQueries(int[] arr, int[][] queries) {
        this.arr = arr;
        int[] result = new int[queries.length];
        if (arr.length!=0) {
            root = constructTree(0,arr.length-1);
        }
        int i=0;
        for (int[] query : queries) {
            result[i++]=calculate(query[0],query[1],root);
        }
        return result;
    }

    private int calculate(int start,int end,SegmentNode root) {
        if(root==null || start>end || start>root.range[1] || end<root.range[0]) {
            return 0;
        }
        if(start<=root.range[0] && root.range[1]<=end) {
            return root.xor;
        }
        return calculate(start,end,root.left)^calculate(start,end,root.right);
    }

    private SegmentNode constructTree(int start,int end) {
        if(start==end) {
            return new SegmentNode(new int[]{start,start},arr[start]);
        }
        int mid = start+(end-start)/2;
        SegmentNode left = constructTree(start,mid);
        SegmentNode right = constructTree(mid+1,end);
        SegmentNode node = new SegmentNode(new int[]{start,end},left.xor^right.xor);
        node.left = left;node.right=right;
        return node;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1,2}
        };
        System.out.println(Arrays.toString(new XORQueries().xorQueries(new int[]{1,3,4,8},a)));
    }

}


/*

Better Solution:
Prefix XOR:
// We have:
prefixXOR[0] = 0
prefixXOR[1] = arr[0]
prefixXOR[2] = arr[0] ^ arr[1]
...
prefixXOR[n] = arr[0] ^ arr[1] ^ ... ^ arr[n-1]

// So,
prefixXOR[r+1] ^ prefixXOR[l]
= (arr[0] ^ arr[1] ^ ... ^ arr[r]) ^ (arr[0] ^ arr[1] ^ ... ^ arr[l-1])
= arr[0] ^ arr[0] ^ arr[1] ^ arr[1] ^ ... ^ arr[l] ^ arr[l+1] ^ ... ^ arr[r]
= arr[l] ^ arr[l+1] ^ ... ^ arr[r] // Because A ^ A ^ B = B
= answer


        int[] prefixXor = new int[arr.length + 1];
        for (int i = 0; i < arr.length; ++i)
            prefixXor[i + 1] = prefixXor[i] ^ arr[i];
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; ++i)
            ans[i] = prefixXor[queries[i][1] + 1] ^ prefixXor[queries[i][0]];
        return ans;
 */