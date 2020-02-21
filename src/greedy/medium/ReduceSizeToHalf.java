/*

Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.

Return the minimum size of the set so that at least half of the integers of the array are removed.



Example 1:

Input: arr = [3,3,3,3,5,5,5,2,2,7]
Output: 2
Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half of the size of the old array.
Example 2:

Input: arr = [7,7,7,7,7,7]
Output: 1
Explanation: The only possible set you can choose is {7}. This will make the new array empty.
Example 3:

Input: arr = [1,9]
Output: 1
Example 4:

Input: arr = [1000,1000,3,7]
Output: 1
Example 5:

Input: arr = [1,2,3,4,5,6,7,8,9,10]
Output: 5


Constraints:

1 <= arr.length <= 10^5
arr.length is even.
1 <= arr[i] <= 10^5

 */
package greedy.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReduceSizeToHalf {

    public int minSetSize(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        int max = 0;
        // Calculate frequency.
        for(int x : arr) {
            map.put(x,map.getOrDefault(x,0)+1);
            max = Math.max(max,map.get(x));
        }
        // Bucketize elements according to frequency
        List<Integer>[] a = new List[max+1];
        for(Map.Entry<Integer,Integer> entry : map.entrySet()) {
            int val = entry.getValue();
            if(a[val]==null) {
                a[val]=new ArrayList<>();
            }
            a[val].add(entry.getKey());
        }
        int count=0;
        int len = arr.length;
        //Count backwards, return once half size is reached.
        for(int i=max;i>=0;i--) {
            if(a[i]!=null) {
                if(len<=arr.length/2) {
                    break;
                }
                int size = a[i].size();
                int k = 1;
                int l = len;
                while(k<=size) {
                    l=l-i;
                    k++;
                    count++;
                    if(l<=arr.length/2) {
                        break;
                    }
                }
                len = l;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new ReduceSizeToHalf().minSetSize(new int[]{2,28,92,30,100,52,28,48,91,27,66,19,11,53,91,95,
                74,51,65,65,96,81,21,55,98,3,2,89,99,57,78,34,50,2,57,76,23,90,89,36,53,22,73,59,95,45})); // Ans: 14
    }

}
