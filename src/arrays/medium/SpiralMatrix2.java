/*

Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]

 */

package arrays.medium;

/**
 * Created by poorvank on 24/12/16.
 */
public class SpiralMatrix2 {

    public int[][] generateMatrix(int n) {

        int[][] arr = new int[n][n];

        if(n==0) {
            return arr;
        }

        int lastElement  = n*n;

        fillTopRight(arr,0,0,n-1,n-1,1,lastElement);


        return arr;

    }

    private void fillTopRight(int[][] arr,int x1,int y1,int x2,int y2,int currentElement,int lastElement) {

        for (int j=x1;j<=y2 && (currentElement<=lastElement);j++) {
            arr[x1][j] = currentElement++;
        }

        for (int i=y1+1;i<=x2 && (currentElement<=lastElement);i++) {
            arr[i][y2] = currentElement++;
        }

        if(currentElement<=lastElement) {
            fillBottomLeft(arr,x1+1,y1,x2,y2-1,currentElement,lastElement);
        }

    }

    private void fillBottomLeft(int[][] arr,int x1,int y1,int x2,int y2,int currentElement,int lastElement) {

        for (int j=y2;j>=y1 && (currentElement<=lastElement);j--) {
            arr[x2][j] = currentElement++;
        }

        for (int i=x2-1;i>=x1 && (currentElement<=lastElement);i--) {
            arr[i][y1] = currentElement++;
        }

        if(currentElement<=lastElement) {
            fillTopRight(arr,x1,y1+1,x2-1,y2,currentElement,lastElement);
        }

    }

    public static void main(String[] args) {
        int n = 5;
        int[][] arr = new SpiralMatrix2().generateMatrix(n);

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

    }

}
