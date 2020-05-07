/*

Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:

Input: matrix = [[1,0,1],[0,-2,3]], k = 2
Output: 2
Explanation: Because the sum of rectangle [[0, 1], [-2, 3]] is 2,
             and 2 is the max number no larger than k (k = 2).
Note:

The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?

 */
package arrays.hard;
import java.util.TreeSet;

public class MaximumSumSubMatrix {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int result = Integer.MIN_VALUE;
        if(m>n) {
            for(int j=0;j<n;j++) {
                int[] sub = new int[m];
                for(int current=j;current<n;current++) {
                    for(int i=0;i<m;i++) {
                        sub[i]+=matrix[i][current];
                    }
                    result = Math.max(result,getSmallestSum(sub,k));
                }
            }
        } else {
            for(int i=0;i<m;i++) {
                int[] sub = new int[n];
                for(int current=i;current<m;current++) {
                    for(int j=0;j<n;j++) {
                        sub[j]+=matrix[current][j];
                    }
                    result = Math.max(result,getSmallestSum(sub,k));
                }
            }
        }
        return result;
    }

    private int getSmallestSum(int[] arr,int k) {
        TreeSet<Integer> set = new TreeSet<>();
        int sum=0;
        set.add(0);
        int res=Integer.MIN_VALUE;
        for(int a : arr) {
            sum+=a;
            Integer ceil = set.ceiling(sum-k);
            if(ceil!=null) {
                res = Math.max(res,sum-ceil);
            }
            set.add(sum);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {5, -4,-3,4},
                {-3,-4,4, 5},
                {5, 1, 5,-4}
        };
        System.out.println(new MaximumSumSubMatrix().maxSumSubmatrix(a,8));
    }

}

/*

compute the cumulative sum of the array
find a pair of i and j, constrained to i<j, and cum[j]-cum[i]<=k
do some trick, the inequation above is actually cum[j]-k<=cum[i],
we need to find the minimum value of cum[i] in order to maximize cum[j]-cum[i], that is, find TreeSet.ceiling(cum[j]-k)

The time complexity will be O(r^2clogc) where r is the number of rows and c is the number of columns.
If r is much larger than c, the complexity can be O(c^2rlogr) by creating a row-sum array instead of column-sum array

 */