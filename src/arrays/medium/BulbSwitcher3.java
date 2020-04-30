/*
There is a room with n bulbs, numbered from 1 to n, arranged in a row from left to right. Initially, all the bulbs are turned off.

At moment k (for k from 0 to n - 1), we turn on the light[k] bulb. A bulb change color to blue only if it is on and all the previous bulbs (to the left) are turned on too.

Return the number of moments in which all turned on bulbs are blue.

Example 1:
Input: light = [2,1,3,5,4]
Output: 3
Explanation: All bulbs turned on, are blue at the moment 1, 2 and 4.
Example 2:

Input: light = [3,2,4,1,5]
Output: 2
Explanation: All bulbs turned on, are blue at the moment 3, and 4 (index-0).
Example 3:

Input: light = [4,1,2,3]
Output: 1
Explanation: All bulbs turned on, are blue at the moment 3 (index-0).
Bulb 4th changes to blue at the moment 3.
Example 4:

Input: light = [2,1,4,3,6,5]
Output: 3
Example 5:

Input: light = [1,2,3,4,5,6]
Output: 6


Constraints:

n == light.length
1 <= n <= 5 * 10^4
light is a permutation of  [1, 2, ..., n]
 */
package arrays.medium;

public class BulbSwitcher3 {

    public int numTimesAllBlue(int[] light) {

        int n = light.length;
        // Check which ones are yellow.
        boolean[] isYellow = new boolean[n];
        //Check which ones are blue.
        boolean[] isBlue = new boolean[n];
        // Count of current yellow bulbs.
        int yellowCount=0;
        int ans=0;
        for (int l : light) {
            int toLight = l-1; // 0 indexed.
            isYellow[toLight]=true;
            yellowCount++;
            // Check if previous ones are blue, then make this blue.
            if(toLight==0 || isBlue[toLight-1]) {
                isBlue[toLight]=true;
                isYellow[toLight]=false;
                yellowCount--;
            }
            // Check if forwards are yellow make them blue, only if current is blue
            if(isBlue[toLight]) {
                for (int i=toLight+1;i<n;i++) {
                    if(isYellow[i]) {
                        isBlue[i]=true;
                        yellowCount--;
                    } else {
                        break;
                    }
                }
            }
            //Check if total yellow count is 0.
            if(yellowCount==0) {
                ans++;
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        System.out.println(new BulbSwitcher3().numTimesAllBlue(new int[]{4,1,2,3}));
    }

}

/*

Better sol:

right is the number of the right most lighted bulb.

Iterate the input light A,
update right = max(right, A[i]).

Now we have lighted up i + 1 bulbs,
if right == i + 1,
it means that all the previous bulbs (to the left) are turned on too.
Then we increment res


Complexity
Time O(N)
Space O(1)

public int numTimesAllBlue(int[] A) {
        int right = 0, res = 0, n = A.length;
        for (int i = 0; i < n; ++i) {
            right = Math.max(right, A[i]);
            if (right == i + 1) ++res;
        }
        return res;
    }

Using Math:

“1 +2 + ... + N” is called the “nth triangle number” and equals “n*(n + 1)/2”.
This is used to check if the sum of the numbers up to the current seen max is
equal to current sum of the numbers seen - if it is, all of the elements to the
left have been seen and so it is a moment that satisfies the problem.

public int numTimesAllBlue(int[] light) {
        int n = light.length, ans = 0, currMax = 0;
        long currSum = 0;
        for(int i = 0; i < n; i++) {
            currMax = Math.max(currMax, light[i]);
            currSum += (long)light[i];
            if(currSum == ((long)currMax * (currMax + 1)) / 2) ans++;
        }
        return ans;
    }

 */