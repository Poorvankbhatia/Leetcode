/*

Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time.

Return that integer.



Example 1:

Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6


Constraints:

1 <= arr.length <= 10^4
0 <= arr[i] <= 10^5

 */
package string.medium;

import java.util.PriorityQueue;

public class CombinationIterator {

    PriorityQueue<String> pq = new PriorityQueue<>();
    public CombinationIterator(String s, int k) {
        generateSub(s,k,0,new StringBuilder());
    }
    private void generateSub(String s ,int len,int start,StringBuilder result) {
        if (len == 0){
            pq.add(result.toString());
            return;
        }
        for (int i = start; i <= s.length()-len; i++){
            result.append(s.charAt(i));
            generateSub(s, len-1, i+1, result);
            result.deleteCharAt(result.length()-1);
        }
    }
    public String next() {
        return pq.poll();
    }
    public boolean hasNext() {
        return !pq.isEmpty();
    }

}
