/*

Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.


 */

package dyanamicprogramming.hard;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by poorvank on 04/02/17.
 */
public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {

        if(null==matrix || matrix.length==0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[] height = new int[cols];

        for (int i=0;i<cols;i++) {
            height[i] = matrix[0][i]=='1'?1:0;
        }

        int max = maxAreaHistogram(height);

        for (int i=1;i<rows;i++) {

            for (int j=0;j<cols;j++) {
                if(matrix[i][j]=='1') {
                    height[j] += 1;
                } else {
                    height[j] = 0;
                }
            }

            max = Math.max(max,maxAreaHistogram(height));

        }

        return max;

    }

    private int maxAreaHistogram(int[] arr) {

        Stack<Integer> leftStack = new Stack<>();
        Stack<Integer> rightStack = new Stack<>();
        int n = arr.length;
        int[] width = new int[n];

        Arrays.fill(width,1);

        leftStack.push(0);

        for (int i=1;i<n;i++) {

            while (!leftStack.isEmpty() && arr[leftStack.peek()]>=arr[i]) {
                leftStack.pop();
            }

            if(leftStack.isEmpty()) {
                width[i] += i;
            } else {
                width[i] += i-leftStack.peek()-1;
            }

            leftStack.push(i);

        }

        rightStack.push(n-1);

        for (int i=n-2;i>=0;i--) {

            while (!rightStack.isEmpty() && arr[rightStack.peek()]>=arr[i]) {
                rightStack.pop();
            }

            if(rightStack.isEmpty()) {
                width[i] += n-1-i;
            } else {
                width[i] += rightStack.peek()-i-1;
            }

            rightStack.push(i);

        }

        int max=0;

        for (int i=0;i<n;i++) {

            max = Math.max(max,(arr[i]*width[i]));

        }

        return max;

    }

    public static void main(String[] args) {
        char[][] matrix = new char[][] {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };

        System.out.println(new MaximalRectangle().maximalRectangle(matrix));
    }

}


/*


Input :   0 1 1 0
          1 1 1 1
          1 1 1 1
          1 1 0 0

Output :  1 1 1 1
          1 1 1 1

 largest rectangle under histogram as a subroutine. Below are steps.
 The idea is to update each column of a given row with corresponding column of previous row and find largest histogram area for for that row.

Step 1: Find maximum area for row[0]
Step 2:
    for each row in 1 to N - 1
        for each column in that row
            if A[row][column] == 1
              update A[row][column] with
                A[row][column] += A[row - 1][column]
    find area for that row
    and update maximum area so far
Illustration :

step 1:    0 1 1 0  maximum area  = 2
step 2:
    row 1  1 2 2 1  area = 4, maximum area becomes 4
    row 2  2 3 3 2  area = 8, maximum area becomes 8
    row 3  3 4 0 0  area = 6, maximum area remains 8

 */