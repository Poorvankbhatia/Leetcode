/*

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
You should return [1,2,3,6,9,8,7,4,5].

 */
package arrays.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 24/12/16.
 */
public class SpiralMatrix {

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();
        int rows = matrix.length;

        if(rows==0) {
            return result;
        }

        int cols = matrix[0].length;

        if(rows==1) {
            for (int j=0;j<cols;j++) {
                result.add(matrix[0][j]);
            }
            return result;
        }

        if(cols==1) {
            for (int[] aMatrix : matrix) {
                result.add(aMatrix[0]);
            }
            return result;
        }

        printTopRight(matrix,0,0,rows-1,cols-1,result);
        return result;

    }

    private void printTopRight(int[][] matrix,int x1,int y1,int x2,int y2,List<Integer> list) {

        for (int j=y1;j<=y2;j++) {
            list.add(matrix[x1][j]);
        }

        for (int i=x1+1;i<=x2;i++) {
            list.add(matrix[i][y2]);
        }

        if (y2-y1!=0 && x2-x1!=0) {
            printBottomLeft(matrix,x1+1,y1,x2,y2-1,list);
        }

    }

    private void printBottomLeft(int[][] matrix,int x1,int y1,int x2,int y2,List<Integer> list) {

        for (int j=y2;j>=y1;j--) {
            list.add(matrix[x2][j]);
        }

        for (int i=x2-1;i>=x1;i--) {
            list.add(matrix[i][y1]);
        }

        if (y2-y1!=0 && x2-x1!=0) {
            printTopRight(matrix,x1,y1+1,x2-1,y2,list);
        }

    }

    public static void main(String[] args) {
        int[][] arr = new int[][] {
                {1,11},
                {2,12},
                {3,13},
                {4,14},
                {5,15},
                {6,16}
        };

        System.out.println(new SpiralMatrix().spiralOrder(arr));
    }

}
