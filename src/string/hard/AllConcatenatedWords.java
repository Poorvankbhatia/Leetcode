/*

You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of
substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.

For example, given:
s: "barfoothefoobarman"
words: ["foo", "bar"]

You should return the indices: [0,9].
(order does not matter).

 */
package string.hard;

import java.util.*;

/**
 * Created by poorvank on 19/03/17.
 */
public class AllConcatenatedWords {

    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> pos = new ArrayList<>();

        if(s==null || words==null || s.length()==0 || words.length==0) {
            return pos;
        }
        HashMap<String,Integer> needToFind = new HashMap<>();
        int len = words[0].length();
        int wordsLen = words.length*len;

        if(s.length()<wordsLen){
            return pos;
        }

        for (String word : words) {
            if(needToFind.containsKey(word)) {
                needToFind.put(word,needToFind.get(word)+1);
            } else {
                needToFind.put(word,1);
            }
        }

        for (int i=0;i<len;i++) { //Maximum staring can be till len;
            HashMap<String,Integer> hasFound = new HashMap<>();
            int count =0;
            int start = i;
            for (int end=i;end<=s.length()-len;end+=len) {

                String current = s.substring(end,end+len);

                if(!needToFind.containsKey(current)) {
                    hasFound.clear();
                    //move start pointer
                    start=end+len;
                    count=0;
                } else {
                    if(!hasFound.containsKey(current)) {
                        hasFound.put(current,1);
                    } else {
                        hasFound.put(current,hasFound.get(current)+1);
                    }

                    count+=len;

                    /*
                    For example:
                    "barfoofoobarthefoobarman"
                     ["bar","foo","the"]
                     */
                    while(hasFound.get(current)>needToFind.get(current)){
                        String left = s.substring(start, start+len);
                        hasFound.put(left, hasFound.get(left)-1);

                        count-=len;
                        start = start + len;
                    }

                    if(count==wordsLen) {
                        pos.add(start);
                        String left = s.substring(start, start+len);
                        hasFound.put(left, hasFound.get(left)-1);
                        count-=len;
                        start = start + len;
                    }
                }

            }

        }

        return pos;

    }

    public static void main(String[] args) {

        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar","foo","the"};

        System.out.print(new AllConcatenatedWords().findSubstring(s,words));

    }

}

/*

Very Similar to minimum window Substring

 */
