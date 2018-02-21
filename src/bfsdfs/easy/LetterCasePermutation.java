/*

Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length at most 12.
S will consist only of letters or digits.

 */

package bfsdfs.easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by poorvank.b on 21/02/18.
 */
public class LetterCasePermutation {

    public List<String> letterCasePermutation(String S) {

        int index=0;
        List<String> result = new ArrayList<>();

        if(S==null || S.length()==0) {
            result.add("");
            return result;
        }

        Queue<String> queue = new LinkedList<>();
        if(Character.isLetter(S.charAt(index))) {
            queue.add(Character.toLowerCase(S.charAt(index))+"");
            queue.add(Character.toUpperCase(S.charAt(index))+"");
        } else {
            queue.add(S.charAt(index)+"");
        }

        index++;
        while (index<S.length()) {

            int size = queue.size();
            if(Character.isLetter(S.charAt(index))) {
                for (int i=0;i<size;i++) {
                    String current = queue.isEmpty()?"":queue.poll();
                    queue.add(current+Character.toLowerCase(S.charAt(index)));
                    queue.add(current+Character.toUpperCase(S.charAt(index)));
                }
            } else {
                for (int i=0;i<size;i++) {
                    String current = queue.isEmpty()?"":queue.poll();
                    queue.add(current+S.charAt(index));
                }
            }

            index++;

        }

        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }


        return result;

    }


    public static void main(String[] args) {
        String s = "";
        System.out.println(new LetterCasePermutation().letterCasePermutation(s));
    }

}

/*

BETTER METHOD

See the figure below:
abc
abc Abc 0
abc aBc Abc ABc 1
abc abC aBc aBC Abc AbC ABc ABC 2

There we go! Is that a typical BFS/DFS problem? Yes, you are right!
Be careful to check whether a character is a digit or a letter(lower case or upper case).

class Solution {
    public List<String> letterCasePermutation(String S) {
        if (S == null) {
            return new LinkedList<>();
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(S);

        for (int i = 0; i < S.length(); i++) {
            if (Character.isDigit(S.charAt(i))) continue;
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                String cur = queue.poll();
                char[] chs = cur.toCharArray();

                chs[i] = Character.toUpperCase(chs[i]);
                queue.offer(String.valueOf(chs));

                chs[i] = Character.toLowerCase(chs[i]);
                queue.offer(String.valueOf(chs));
            }
        }

        return new LinkedList<>(queue);
    }
}

 */