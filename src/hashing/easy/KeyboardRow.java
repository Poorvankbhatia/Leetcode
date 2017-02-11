/*

Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard

Example 1:
Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]
Note:
You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.


 */
package hashing.easy;

import java.util.*;

/**
 * Created by poorvank on 08/02/17.
 */
public class KeyboardRow {

    public String[] findWords(String[] words) {

        if(null==words || words.length==0) {
            return new String[0];
        }

        String[] keyboard = new String[]{"QWERTYUIOP","ASDFGHJKL","ZXCVBNM"};

        Map<Character,Integer> map = new HashMap<>();

        for (int i=0;i<keyboard.length;i++) {
            for (Character c : keyboard[i].toCharArray()) {
                map.put(c,i);
            }
        }

        List<String> list = new ArrayList<>();

        for (String word : words) {
            int row = map.get(Character.toUpperCase(word.charAt(0)));
            int i;
            for (i=1;i<word.length();i++) {
                int currentCharRow = map.get(Character.toUpperCase(word.charAt(i)));
                if(currentCharRow!=row) {
                    break;
                }
            }
            if(i==word.length()){
                list.add(word);
            }
        }


        return list.toArray(new String[list.size()]);

    }

    public static void main(String[] args) {
        String[] arr = new String[]{"asdf","sdf"};
        System.out.print(Arrays.toString(new KeyboardRow().findWords(arr)));
    }

}
