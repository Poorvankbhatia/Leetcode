/*

A string S of lowercase letters is given. We want to partition this string into as many parts as possible
so that each letter appears in at most one part, and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
Note:

S will have length in range [1, 500].
S will consist of lowercase letters ('a' to 'z') only.

 */

package string.medium;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank.b on 14/01/18.
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        if(S == null || S.length() == 0){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        int[] map = new int[26];  // record the last index of the each char

        for(int i = 0; i < S.length(); i++){
            map[S.charAt(i)-'a'] = i;
        }
        // record the end index of the current sub string
        int last = 0;
        int start = 0;
        for(int i = 0; i < S.length(); i++){
            last = Math.max(last, map[S.charAt(i)-'a']);
            if(last == i){
                list.add(last - start + 1);
                start = last + 1;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(new PartitionLabels().partitionLabels("ababcbacadefegdehijhklij"));
    }

}

/*

traverse the string record the last index of each char.
using pointer to record end of the current sub string.

Another sol : same as interval list intersection:
public List<Integer> partitionLabels(String S) {
        List<int[]> sortedIntervalList = new ArrayList<>(); // Sorted list of intervals of every character occurring.
        Map<Character,int[]> map = new HashMap<>();
        int n = S.length();
        for(int i=0;i<n;i++) {
            char c = S.charAt(i);
            if(!map.containsKey(c)) {
                map.put(c,new int[]{i,i});
            } else {
                map.put(c,new int[]{map.get(c)[0],i});
            }
        }
        for(char c : S.toCharArray()) {
            if(map.containsKey(c)) {
                sortedIntervalList.add(map.get(c));
                map.remove(c);
            }
        }
        List<int[]> mergedList = new ArrayList<>(); // Merged intervals.
        mergedList.add(sortedIntervalList.get(0));
        List<Integer> result = new ArrayList<>();
        int k=0;
        for (int i=1;i<sortedIntervalList.size();i++) {
            if (sortedIntervalList.get(i)[0]<=mergedList.get(k)[1]) {
                int[] merge = new int[]{mergedList.get(k)[0],Math.max(mergedList.get(k)[1],sortedIntervalList.get(i)[1])};
                //Remove old and merge
                mergedList.remove(k);
                mergedList.add(k,merge);
            } else {
                //Add the new one.
                k++;
                mergedList.add(sortedIntervalList.get(i));
            }
        }

        for (int[] ints : mergedList) {
            result.add(ints[1] - ints[0] + 1);
        }
        return result;
    }

 */