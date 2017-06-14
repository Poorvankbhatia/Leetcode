/*

Design and implement a data structure for a compressed string iterator. It should support the following operations: next and hasNext.

The given compressed string will be in the form of each letter followed by a positive integer representing the number of this letter
existing in the original uncompressed string.

next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white space.
hasNext() - Judge whether there is any letter needs to be uncompressed.

Note:
Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted across multiple
test cases. Please see here for more details.

Example:

StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

iterator.next(); // return 'L'
iterator.next(); // return 'e'
iterator.next(); // return 'e'
iterator.next(); // return 't'
iterator.next(); // return 'C'
iterator.next(); // return 'o'
iterator.next(); // return 'd'
iterator.hasNext(); // return true
iterator.next(); // return 'e'
iterator.hasNext(); // return false
iterator.next(); // return ' '

 */
package design.easy;

import java.util.*;

/**
 * Created by poorvank on 11/06/17.
 */
public class StringIterator {

    List<Character> characters = new ArrayList<>();
    List<Integer> counts = new ArrayList<>();
    int index = 0;

    public StringIterator(String str) {
        int i = 0;
        while (i < str.length()) {
            characters.add(str.charAt(i));
            int j = i + 1;
            while (j < str.length() && Character.isDigit(str.charAt(j))){
                j++;
            }
            counts.add(Integer.parseInt(str.substring(i + 1, j)));
            i = j;
        }
    }

    public char next() {
        if (!hasNext()) return ' ';

        char result = characters.get(index);
        counts.set(index, counts.get(index) - 1);
        if (counts.get(index) == 0) index++;
        return result;
    }

    public boolean hasNext() {
        return index < characters.size();
    }

    public static void main(String[] args) {
        String s = "L1e2t1C1o1d1e1";
        StringIterator stringIterator = new StringIterator(s);
        System.out.println(
                stringIterator.next());
        System.out.println(
                stringIterator.next());
        System.out.println(
                stringIterator.next());
        System.out.println(
                stringIterator.next());
        System.out.println(
                stringIterator.next());
        System.out.println(
                stringIterator.next());
        System.out.println(
                stringIterator.next());
    }

}
