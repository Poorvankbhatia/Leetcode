/*
Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.


 */
package heap.medium;

import heap.MinPriorityQueue;

/**
 * Created by poorvank on 08/09/16.
 */
public class KthSmallestElementSortedMat {

    private class Element implements Comparable<Element> {
        private int x;
        private int y;
        private int value;

        public Element(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public int compareTo(Element e) {
            if(this.value>e.value) {
                return 1;
            } else if(this.value<e.value) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    public int kthSmallest(int[][] matrix, int k) {

        int n = matrix.length;

        MinPriorityQueue<Element> minPriorityQueue = new MinPriorityQueue<>(n);

        for (int i=0;i<n;i++) {
            minPriorityQueue.insert(new Element(i,0,matrix[i][0]));
        }

        int kthSmallestValue=0;
        for (int i=0;i<k;i++) {

            Element e = minPriorityQueue.deleteMin();
            kthSmallestValue = e.value;

            int nextCol = e.y+1;

            if(nextCol<n) {
                e = new Element(e.x,nextCol,matrix[e.x][nextCol]);
                minPriorityQueue.insert(e);
            } else {
                e = new Element(e.x,nextCol,Integer.MAX_VALUE);
                minPriorityQueue.insert(e);
            }

        }

        return kthSmallestValue;

    }

    public static void main(String[] args) {

        int[][] matrix =  new int[][]{{1,  5,  9},
                                      {10, 11, 13},
                                      {12, 13, 15}};

        int k =8;

        KthSmallestElementSortedMat ks = new KthSmallestElementSortedMat();
        System.out.println(ks.kthSmallest(matrix,k));
    }
}


/*

Time complexity  : O(n) + klog(n)

 */