/*

Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].

You need to return the number of important reverse pairs in the given array.

Example1:

Input: [1,3,2,3,1]
Output: 2
Example2:

Input: [2,4,3,5,1]
Output: 3
Note:
The length of the given array will not exceed 50,000.
All the numbers in the input array are in the range of 32-bit integer.

 */
package segmenttree.hard;

/**
 * Created by poorvank.b on 02/12/17.
 */
public class ReversePairs {

    public int reversePairs(int[] nums) {
        int res=0;
        int n=nums.length;
        SegmentNode segmentNode = new SegmentNode(Integer.MIN_VALUE, Integer.MAX_VALUE);
        for (int j = 1; j < n; j++) {
            add(segmentNode, nums[j-1]);
            res += count(nums[j]*2L+1L,segmentNode);
        }
        return res;
    }

    private class SegmentNode {
        private long min, max;
        private int count;
        private SegmentNode left, right;

        public SegmentNode(long min, long max) {
            this.min = min;
            this.max = max;
            count = 0;
            left = right = null;
        }
    }

    public void add(SegmentNode node,long n) {
        node.count++;
        if (node.min==node.max)return;
        long mid = node.min+(node.max-node.min)/2;
        if (n <= mid) {
            if (node.left ==null) {
                node.left = new SegmentNode(node.min, mid);
            }
            add(node.left,n);
        } else {
            if (node.right ==null) {
                node.right = new SegmentNode(mid+1,node.max);
            }
            add(node.right,n);
        }
    }

    public int count(long n,SegmentNode node) {
        if (node.min>=n) return node.count;
        if (node.max<n) return 0;
        return
                (node.left ==null?0:count(n,node.left))+
                        (node.right ==null?0:count(n,node.right));
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
        System.out.println(new ReversePairs().reversePairs(arr));
    }

}

/*

SEGMENT TREE

With the segment tree we can ask, "how many elements in the tree have value >= X?"
The segment tree contains all values that could be used as i.

It is worth noting that this is NOT O(n log(n)). The height of the segment tree is not based on nums.length. This is more like O(n log(Integer.MAX_VALUE)).

 */