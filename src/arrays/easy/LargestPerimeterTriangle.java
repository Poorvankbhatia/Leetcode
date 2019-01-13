/*

Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero area, formed from 3 of these lengths.

If it is impossible to form any triangle of non-zero area, return 0.



Example 1:

Input: [2,1,2]
Output: 5
Example 2:

Input: [1,2,1]
Output: 0
Example 3:

Input: [3,2,3,4]
Output: 10
Example 4:

Input: [3,6,2,3]
Output: 8


Note:

3 <= A.length <= 10000
1 <= A[i] <= 10^6

 */
package arrays.easy;

import java.util.Arrays;

/**
 * Created by poorvank.b on 13/01/19.
 */
public class LargestPerimeterTriangle {

    class Solution {
        public int largestPerimeter(int[] A) {
            if(A==null || A.length<3) {
                return 0;
            }

            if(A.length==3) {
                return validSum(A[0],A[1],A[2]);
            }

            Arrays.sort(A);

            for(int j=A.length-1;j>=2;j--) {
                int val = validSum(A[j],A[j-1],A[j-2]);
                if(val!=0) {
                    return val;
                }
            }

            return 0;
        }

        private int validSum(int a,int b,int c) {
            return ((a+b)>c && (a+c)>b && (b+c)>a)?(a+b+c):0;
        }

    }

}


