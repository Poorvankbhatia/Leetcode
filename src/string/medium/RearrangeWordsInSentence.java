/*
Given a sentence text (A sentence is a string of space-separated words) in the following format:

First letter is in upper case.
Each word in text are separated by a single space.
Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths.
If two words have the same length, arrange them in their original order.

Return the new text following the format shown above.



Example 1:

Input: text = "Leetcode is cool"
Output: "Is cool leetcode"
Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and "cool" of length 4.
Output is ordered by length and the new first word starts with capital letter.
Example 2:

Input: text = "Keep calm and code on"
Output: "On and keep calm code"
Explanation: Output is ordered as follows:
"On" 2 letters.
"and" 3 letters.
"keep" 4 letters in case of tie order by position in original text.
"calm" 4 letters.
"code" 4 letters.
Example 3:

Input: text = "To be or not to be"
Output: "To be or to be not"


Constraints:

text begins with a capital letter and then contains lowercase letters and single space between words.
1 <= text.length <= 10^5
 */
package string.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RearrangeWordsInSentence {

    private static class Element {
        private final String text;
        private final int index;

        Element(String text,int index) {
            this.text = text;
            this.index = index;
        }

    }

    public String arrangeWords(String text) {
        List<Element> list = new ArrayList<>();
        int i=0;
        for(String t : text.split(" ")) {
            Element e = new Element(t, i++);
            list.add(e);
        }
        list.sort((a, b) -> b.text.length() != a.text.length() ? a.text.length() - b.text.length() : a.index - b.index);
        StringBuilder sb = new StringBuilder();
        i=0;
        for(Element s : list) {
            if(i==0) {
                sb.append(s.text.substring(0, 1).toUpperCase()).append(s.text.substring(1)).append(" ");
            } else {
                sb.append(s.text.toLowerCase()).append(" ");
            }
            i++;
        }

        return sb.toString().trim();
    }

}
