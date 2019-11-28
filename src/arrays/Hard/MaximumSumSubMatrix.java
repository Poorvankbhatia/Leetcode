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
package arrays.Hard;
import java.util.TreeSet;

public class MaximumSumSubMatrix {

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        if(m==0) {
            return 0;
        }
        int n = matrix[0].length;
        int ans=Integer.MIN_VALUE;
        for(int j = 0; j < n; j++) {
            int[] sum = new int[m];
            for(int current = j; current < n; current++) {
                for(int i = 0; i < m; i++) {
                    sum[i] += matrix[i][current];
                }
                int temp = maxSumLessThanK(sum, k);
                ans = Math.max(temp, ans);
            }
        }
        return ans;
    }

    private int maxSumLessThanK(int[] arr,int k) {
        TreeSet<Integer> set = new TreeSet<>();
        int result=Integer.MIN_VALUE;
        set.add(0);
        int sum=0;
        for (int a : arr) {
            sum = sum + a;
            Integer ceiling = set.ceiling(sum - k);
            if (ceiling != null) {
                result = Math.max(result, sum - ceiling);
            }
            set.add(sum);
        }
        return result;
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

 */