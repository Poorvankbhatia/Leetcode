/*

Given an integer n, return any array containing n unique integers such that they add up to 0.



Example 1:

Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
Example 2:

Input: n = 3
Output: [-1,0,1]
Example 3:

Input: n = 1
Output: [0]


Constraints:

1 <= n <= 1000

 */
package arrays.easy;

import java.util.Arrays;

public class UniqueIntegerSumToZero {

    public int[] sumZero(int n) {
        int[] result = new int[n];
        if(n%2==0) {
            util(result,0,n);
        } else {
            result[0]=0;
            util(result,1,n);
        }
        return result;
    }

    private void util(int[] result,int k,int n) {
        for(int i=1;i<=n/2;i++) {
            result[k++]=i;
            result[k++]=-i;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new UniqueIntegerSumToZero().sumZero(7)));
    }


}

/*

Better sol:

public int[] sumZero(int n) {
        int arr[] = new int[n];
        int sum = 0;
        for(int i = 0; i < n-1; i++) {
            arr[i] = i+1;
            sum += arr[i];
        }
        arr[n-1] = -sum;
        return arr;
    }

 */