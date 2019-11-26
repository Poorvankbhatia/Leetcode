/*

Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters
of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order.
If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output:
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]

Output:
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.

 */
package string.medium;

import java.util.*;

/**
 * Created by poorvank on 26/02/17.
 */
public class LongestWordDictionary {

    public String findLongestWord(String s, List<String> d) {
        Map<Character,List<Integer>> map = new HashMap<>();
        int i=0;
        for(char c : s.toCharArray()) {
            if(!map.containsKey(c)) {
                map.put(c,new ArrayList<>());
            }
            map.get(c).add(i++);
        }
        String ans="";
        for (String str : d) {
            int prev = -1;
            boolean isSub=true;
            for (char c : str.toCharArray()) {
                if(map.get(c)==null) {
                    isSub = false;
                    break;
                }
                List<Integer> occurrence = map.get(c);
                int index = bs(prev,occurrence);
                if(index==-1) {
                    isSub=false;
                    break;
                }
                prev=index;
            }
            if(isSub) {
                if(str.length()>ans.length() || (str.length()==ans.length() && str.compareTo(ans)<0)) {
                    ans = str;
                }
            }
        }
        return ans;
    }
    // Binary search to find next possible smallest index.
    private int bs(int prev, List<Integer> list) {
        if(prev>list.get(list.size()-1)) {
            return -1;
        }
        int lo = 0;
        int hi = list.size()-1;
        while (lo<hi) {
            int mid = lo+(hi-lo)/2;
            if(list.get(mid)>prev) {
                hi=mid;
            } else {
                lo=mid+1;
            }
        }
        return list.get(lo);
    }

    public static void main(String[] args) {
        String s = "aewfafwafjlwajflwajflwafj";
        List<String> d = new ArrayList<>(Arrays.asList("apple","ewaf","awefawfwaf","awef","awefe","ewafeffewafewf"));
        System.out.println(new LongestWordDictionary().findLongestWord(s,d));
    }

}

/*

Without Sorting & BS:

public String findLongestWord(String s, List<String> d) {
    String longest = "";
    for (String dictWord : d) {
        int i = 0;
        for (char c : s.toCharArray())
            if (i < dictWord.length() && c == dictWord.charAt(i)) i++;

        if (i == dictWord.length() && dictWord.length() >= longest.length())
            if (dictWord.length() > longest.length() || dictWord.compareTo(longest) < 0)
                longest = dictWord;
    }
    return longest;
}

Sort the dictionary via length and lexicographically and then check for every string

Lambda method:
Collections.sort(d, (a,b) -> a.length() != b.length() ? -Integer.compare(a.length(), b.length()) :  a.compareTo(b));

Using sorting & BS:

class Solution {
    public String findLongestWord(String s, List<String> d) {
        Map<Character,List<Integer>> map = new HashMap<>();
        int i=0;
        for(char c : s.toCharArray()) {
            if(!map.containsKey(c)) {
                map.put(c,new ArrayList<>());
            }
            map.get(c).add(i++);
        }
        d.sort((a, b) -> (b.length()!=a.length()?b.length()-a.length():a.compareTo(b)));
        for (String str : d) {
            int prev = -1;
            boolean isSub=true;
            for (char c : str.toCharArray()) {
                if(map.get(c)==null) {
                    isSub = false;
                    break;
                }
                List<Integer> occurrence = map.get(c);
                int index = bs(prev,occurrence);
                if(index==-1) {
                    isSub=false;
                    break;
                }
                prev=index;
            }
            if(isSub) {
                return str;
            }
        }
        return "";
    }

    // Binary search to find next possible smallest index.
    private int bs(int prev, List<Integer> list) {
        if(prev>list.get(list.size()-1)) {
            return -1;
        }
        int lo = 0;
        int hi = list.size()-1;
        while (lo<hi) {
            int mid = lo+(hi-lo)/2;
            if(list.get(mid)>prev) {
                hi=mid;
            } else {
                lo=mid+1;
            }
        }
        return list.get(lo);
    }
}

 */
