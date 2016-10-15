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
