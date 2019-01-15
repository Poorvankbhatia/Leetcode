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


O(n) method stack:

public String removeDuplicateLetters(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int[] count = new int[256];
        for(int i=0;i<n;i++) {
            count[s.charAt(i)-'a']++;
        }

        boolean[] visited = new boolean[256];

        Stack<Character> stack = new Stack<>();

        for(char c : s.toCharArray()) {
            count[c-'a']--;
            if(visited[c-'a']) {
                continue;
            }
            while(!stack.isEmpty() && stack.peek()>c && count[stack.peek()-'a']>0) {
                visited[stack.pop()-'a']=false;
            }
            stack.push(c);
            visited[c-'a']=true;

        }

        for(char c : stack) {
            sb.append(c);
        }

        return sb.toString();

    }

   First, given "bcabc", the solution should be "abc". If we think about this problem intuitively, you would sort of go
   from the beginning of the string and start removing one if there is still the same character left and a smaller character
   is after it. Given "bcabc", when you see a 'b', keep it and continue with the search, then keep the following 'c', then we see
   an 'a'. Now we get a chance to get a smaller lexi order, you can check if after 'a', there is still 'b' and 'c' or not.
   We indeed have them and "abc" will be our result.

Come to the implementation, we need some data structure to store the previous characters 'b' and 'c', and we need to compare
the current character with previous saved ones, and if there are multiple same characters, we prefer left ones. This calls for a stack.

After we decided to use stack, the implementation becomes clearer. From the intuition, we know that we need to know if there are
still remaining characters left or not. So we need to iterate the array and save how many each characters are there. A visited array
is also required since we want unique character in the solution. The line while(!stack.isEmpty() && stack.peek() > c && count[stack.peek()-'a'] > 0)
checks that the queued character should be removed or not, like the 'b' and 'c' in the previous example.
After removing the previous characters, push in the new char and mark the visited array.

Time complexity: O(n), n is the number of chars in string.

Space complexity: O(n) worst case.




 */
