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

    public class StringComparator implements Comparator<String> {

        public int compare(String s1,String s2) {
            if(s1.length()<s2.length()) {
                return 1;
            } else if(s2.length()<s1.length()) {
                return -1;
            } else {
                return s1.compareTo(s2);
            }
        }

    }



    public String findLongestWord(String s, List<String> d) {

        Collections.sort(d,new StringComparator());
        System.out.println(d.toString());

       for (String dictString : d) {
           int index = 0;
           for (Character c : s.toCharArray()) {
               if(index<dictString.length() && dictString.charAt(index)==c) {
                   index++;
               }
           }
           if(index==dictString.length()) {
               return dictString;
           }

       }

        return "";

    }

    public static void main(String[] args) {
        String s = "aewfafwafjlwajflwajflwafj";
        List<String> d = new ArrayList<>(Arrays.asList("apple","ewaf","awefawfwaf","awef","awefe","ewafeffewafewf"));
        System.out.println(new LongestWordDictionary().findLongestWord(s,d));
    }

}

/*

Sort the dictionary via length and lexicographically and then check for every string

Lambda method:
Collections.sort(d, (a,b) -> a.length() != b.length() ? -Integer.compare(a.length(), b.length()) :  a.compareTo(b));
 */
