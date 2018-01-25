/*

Given a string which contains only lowercase letters, remove duplicate letters so that every letter
appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example:
Given "bcabc"
Return "abc"

Given "cbacdcbc"
Return "acdb"

 */

package greedy.hard;

import java.util.HashMap;

/**
 * Created by poorvank on 16/12/16.
 */
public class RemoveDuplicates {

    public String removeDuplicateLetters(String s) {

        if(s.length()==0) {
            return "";
        }

        HashMap<Character,Integer> lastOccurrence = new HashMap<>();
        for (int i=0;i<s.length();i++) {
            lastOccurrence.put(s.charAt(i),i);
        }

        int start = 0;
        int end = getMinEndingValue(lastOccurrence);

        StringBuilder sb = new StringBuilder();

        while (!lastOccurrence.isEmpty()) {
            int min = Integer.MAX_VALUE;
            int index = 0;
            char k = '\0';
            for (int i=start;i<=end;i++) {

                char c = s.charAt(i);
                if(c<min && lastOccurrence.containsKey(c)) {
                    k = c;
                    index = i;
                    min = c;
                }

            }

            sb.append(k);
            lastOccurrence.remove(k);

            start = index+1;
            end = getMinEndingValue(lastOccurrence);


        }

        return sb.toString();

    }


    private int getMinEndingValue(HashMap<Character,Integer> map) {

        int min = Integer.MAX_VALUE;
        for (Character key : map.keySet()){
            min = Math.min(min,map.get(key));
        }
        return min;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveDuplicates().removeDuplicateLetters("cbacdcbc"));
    }

}

/*

The basic idea is to find out the smallest result letter by letter (one letter at a time).
for input "cbacdcbc":

find out the last appeared position for each letter;
c - 7
b - 6
a - 2
d - 4
find out the smallest index from the map in step 1 (a - 2);
the first letter in the final result must be the smallest letter from index 0 to index 2;
repeat step 2 to 3 to find out remaining letters.
the smallest letter from index 0 to index 2: a
the smallest letter from index 3 to index 4: c
the smallest letter from index 4 to index 4: d
the smallest letter from index 5 to index 6: b
so the result is "acdb"

Notes:

after one letter is determined in step 3, it need to be removed from the "last appeared position map", and the same letter
should be ignored in the following steps
in step 3, the beginning index of the search range should be the index of previous determined letter plus one

https://discuss.leetcode.com/topic/43469/java-o-n-solution-using-stack-with-detail-explanation

 */
