/*

Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

Example :
Input:
S = "abcde"
words = ["a", "bb", "acd", "ace"]
Output: 3
Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
Note:

All words in words and S will only consists of lowercase letters.
The length of S will be in the range of [1, 50000].
The length of words will be in the range of [1, 5000].
The length of words[i] will be in the range of [1, 50].

 */
package arrays.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by poorvank.b on 10/03/18.
 */
public class MatchingSubsequences {

    public int numMatchingSubseq(String S, String[] words) {

        if(S==null || S.length()==0 || words==null || words.length==0) {
            return 0;
        }

        Map<Character,List<Integer>> map = new HashMap<>();

        for (int i=0;i<S.length();i++) {
            char c = S.charAt(i);
            if(!map.containsKey(c)) {
                map.put(c,new ArrayList<>());
            }
            map.get(c).add(i);
        }

        int count=0;
        for (String word : words) {
            int prev=-1;
            boolean flag=false;
            for (int i=0;i<word.length();i++) {
                char c = word.charAt(i);

                if(!map.containsKey(c)) {
                    flag=true;
                    break;
                } else {
                    List<Integer> list = map.get(c);
                    prev = binarySearch(prev,list,0,list.size()-1);
                    if(prev==-1) {
                        flag=true;
                        break;
                    } else {
                        prev++;
                    }
                }

            }
            if(!flag) {
                count++;
            }
        }

        return count;

    }

    private int binarySearch(int index, List<Integer> children, int start, int end) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (children.get(mid) < index) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return start == children.size() ? -1 : children.get(start);
    }

    public static void main(String[] args) {
        String S = "abcde";
        String[] words = {"acd","a", "bb", "ace"};
        System.out.println(new MatchingSubsequences().numMatchingSubseq(S,words));
    }


}
