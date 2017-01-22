/*

You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.

Define a pair (u,v) which consists of one element from the first array and one element from the second array.

Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.

Example 1:
Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3

Return: [1,2],[1,4],[1,6]

The first 3 pairs are returned from the sequence:
[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
Example 2:
Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2

Return: [1,1],[1,1]

The first 2 pairs are returned from the sequence:
[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
Example 3:
Given nums1 = [1,2], nums2 = [3],  k = 3

Return: [1,3],[2,3]

All possible pairs are returned from the sequence:
[1,3],[2,3]


 */
package heap.medium;

import heap.MaxPriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank on 05/09/16.
 */


public class KPairsWithSmallestSums {

    private class Pair implements Comparable<Pair> {

        private int e1;
        private int e2;
        private int sum;
        private int[] array;

        public Pair(int e1, int e2) {
            this.e1 = e1;
            this.e2 = e2;
            this.sum = e1+e2;
            array = new int[]{e1,e2};
        }

        public int getSum() {
            return sum;
        }

        public int[] getArray() {
            return array;
        }

        public int compareTo(Pair p) {
            if(this.sum>p.getSum()) {
                return 1;
            } else if(this.sum < p.getSum()) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        MaxPriorityQueue<Pair> maxPriorityQueue = new MaxPriorityQueue(k);
        Pair[] newArr = new Pair[nums1.length*nums2.length];

        int x=0;

        for (int i=0;i<nums1.length;i++) {
            for (int j=0;j<nums2.length;j++) {
                Pair p = new Pair(nums1[i],nums2[j]);
                newArr[x] = p;
                if(x<k) {
                    maxPriorityQueue.insert(p);
                }
                x++;
            }
        }

        for (int i=k;i<newArr.length;i++) {
            if(newArr[i].sum<maxPriorityQueue.getMaximumElement().getSum()) {
                maxPriorityQueue.replaceRoot(newArr[i]);
            }
        }

        for (Pair pair : maxPriorityQueue) {
            result.add(pair.array);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums1 = new int[]{1,1,2};
        int[] nums2 = new int[]{1,2,3};
        KPairsWithSmallestSums k = new KPairsWithSmallestSums();
        for (int[] array : k.kSmallestPairs(nums1,nums2,2)) {
            System.out.println(Arrays.toString(array));
        }

    }

}
