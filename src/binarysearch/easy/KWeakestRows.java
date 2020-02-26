/*
Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians), return the indexes of the k
weakest rows in the matrix ordered from the weakest to the strongest.

A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j, or they have
the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is, always ones may appear first and then zeros.



Example 1:

Input: mat =
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]],
k = 3
Output: [2,0,3]
Explanation:
The number of soldiers for each row is:
row 0 -> 2
row 1 -> 4
row 2 -> 1
row 3 -> 2
row 4 -> 5
Rows ordered from the weakest to the strongest are [2,0,3,1,4]
Example 2:

Input: mat =
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]],
k = 2
Output: [0,2]
Explanation:
The number of soldiers for each row is:
row 0 -> 1
row 1 -> 4
row 2 -> 1
row 3 -> 1
Rows ordered from the weakest to the strongest are [0,2,3,1]


Constraints:

m == mat.length
n == mat[i].length
2 <= n, m <= 100
1 <= k <= m
matrix[i][j] is either 0 or 1.
 */
package binarysearch.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KWeakestRows {

    private int m,n;

    public int[] kWeakestRows(int[][] mat, int k) {

        m = mat.length;
        n = mat[0].length;

        List<Integer>[] lists = new List[n+1];

        for (int i=0;i<m;i++) {
            int count = bs(mat[i]);
            if(lists[count]==null) {
                lists[count]=new ArrayList<>();
            }
            lists[count].add(i);
        }

        List<Integer> result = new ArrayList<>();

        for (int i=0;i<=n;i++) {
            if(lists[i]!=null) {
                int size = lists[i].size();
                if (size<=k) {
                    result.addAll(lists[i]);
                } else {
                    result.addAll(lists[i].subList(0,k));
                }
                k = k-size;
                if(k<=0) {
                    break;
                }
            }
        }

        return result.stream().mapToInt(i->i).toArray();
    }

    private int bs(int[] arr) {
        int start=0;
        int end=n;
        while (start<end) {
            int mid = start+(end-start)/2;
            if(arr[mid]==0) {
                end=mid;
            } else {
                start=mid+1;
            }
        }

        return start;

    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,1,1,1,1},
                                  {1,1,1,1,1}};
        System.out.println(Arrays.toString(new KWeakestRows().kWeakestRows(arr,1)));
    }

}
