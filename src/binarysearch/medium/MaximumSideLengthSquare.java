/*

Given a m x n matrix mat and an integer threshold. Return the maximum side-length of a square with a sum less than or
equal to threshold or return 0 if there is no such square.

Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
Output: 2
Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
Example 2:

Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
Output: 0
Example 3:

Input: mat = [[1,1,1,1],[1,0,0,0],[1,0,0,0],[1,0,0,0]], threshold = 6
Output: 3
Example 4:

Input: mat = [[18,70],[61,1],[25,85],[14,40],[11,96],[97,96],[63,45]], threshold = 40184
Output: 2


Constraints:

1 <= m, n <= 300
m == mat.length
n == mat[i].length
0 <= mat[i][j] <= 10000
0 <= threshold <= 10^5

 */
package binarysearch.medium;

public class MaximumSideLengthSquare {

    int[][] sum;
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        sum=new int[m][n];
        int min = 0;
        int max = Math.min(m,n);
        fillSumMatrix(mat);
        int res=0;
        while(min<=max) {
            int mid = (min)+(max-min)/2;
            int val = count(mid,m,n);
            if(val>threshold) {
                max=mid-1;
            } else {
                res=mid;
                min=mid+1;
            }
        }
        return res;
    }

    private void fillSumMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                if(i==0 && j==0) {
                    sum[i][j]=mat[i][j];
                } else if(i==0) {
                    sum[i][j]=mat[i][j]+sum[i][j-1];
                } else if(j==0) {
                    sum[i][j]=mat[i][j]+sum[i-1][j];
                } else {
                    sum[i][j]=mat[i][j]+sum[i-1][j]+sum[i][j-1]-sum[i-1][j-1];
                }
            }
        }
    }

    private int getSubMatrixSum(int x1,int y1,int x2,int y2) {
        if(x1==0 && y1==0) {
            return sum[x2][y2];
        } else if(x1==0) {
            return sum[x2][y2]-sum[x2][y1-1];
        } else if(y1==0) {
            return sum[x2][y2]-sum[x1-1][y2];
        } else{
            return sum[x2][y2]-sum[x2][y1-1]-sum[x1-1][y2]+sum[x1-1][y1-1];
        }
    }


    private int count(int len,int m,int n) {
        if(len==0) {
            return Integer.MAX_VALUE;
        }
        int minSum=Integer.MAX_VALUE;
        for(int i=0;i<m-len+1;i++) {
            for(int j=0;j<n-len+1;j++) {
                minSum = Math.min(minSum,getSubMatrixSum(i,j,i+len-1,j+len-1));
            }
        }
        return minSum;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                /*{1,1,3,2,4,3,2},
                {1,1,3,2,4,3,2},
                {1,1,3,2,4,3,2}*/
                {1,0},
                {0,0}
        };
        System.out.println(new MaximumSideLengthSquare().maxSideLength(a,3));
    }

}

/*

Same Binary search as isSubsequence.

 */