/*
A string s is called good if there are no two different characters in s that have the same frequency.

Given a string s, return the minimum number of characters you need to delete to make s good.

The frequency of a character in a string is the number of times it appears in the string.
For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.



Example 1:

Input: s = "aab"
Output: 0
Explanation: s is already good.
Example 2:

Input: s = "aaabbbcc"
Output: 2
Explanation: You can delete two 'b's resulting in the good string "aaabcc".
Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
Example 3:

Input: s = "ceabaacb"
Output: 2
Explanation: You can delete both 'c's resulting in the good string "eabaab".
Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).


Constraints:

1 <= s.length <= 105
s contains only lowercase English letters.
 */
package string.medium;

import java.util.*;

public class MinDeletionToMakeCharUnique {

    public int minDeletions(String s) {

        // first find the current char frequency.
        int[] arr = new int[26];
        for (char c : s.toCharArray()) {
            arr[c-'a']++;
        }
        // In the map store freq -> List char map. (2->a,b,c)
        Map<Integer, List<Character>> map = new HashMap<>();
        for (int i=0;i<26;i++) {
            if(arr[i]!=0) {
                map.putIfAbsent(arr[i],new ArrayList<>());
                map.get(arr[i]).add((char)(i+'a'));
            }
        }
        int ans = 0;
        // every time a freq of a char is decreased, and it is assigned a new one use newAssign to store that freq..
        Set<Integer> newAssign = new HashSet<>();
        for (Map.Entry<Integer,List<Character>> entry : map.entrySet()) {
            if(entry.getValue().size()>1) {
                int freq = entry.getKey();
                // loop over all the chars with the same frequency, leaving the 0th element
                for (int j=1;j<entry.getValue().size();j++) {
                    // try and reduce the freq and check if already assigned or not.
                    int k= freq-1;
                    while (k>0) {
                        if(!map.containsKey(k) && newAssign.add(k)) {
                            break;
                        }
                        k--;
                    }
                    // means delete the char entirely.
                    if(k==0) {
                        ans+=freq;
                    } else { // delete freq-k occurrences of
                        ans+=freq-k;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new MinDeletionToMakeCharUnique().minDeletions("ababccdefwewdwedwwe"));
    }

}

/*

Better sol:

public int minDeletions(String s) {
        int[] charCount = new int[26];

        for(char ch : s.toCharArray()){
            charCount[ ch - 'a'] ++;
        }

        HashSet<Integer> set = new HashSet<>();
        int deletion = 0;

        for(int val : charCount){
            while(val !=0 && set.contains(val)){
                val--;
                deletion++;
            }
            set.add(val);
        }

        return deletion;
    }

 */
