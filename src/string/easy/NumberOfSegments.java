/*

Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

For example,

Input: "Hello, my name is John"

Output: 5

 */
package string.easy;

/**
 * Created by poorvank on 04/12/16.
 */
public class NumberOfSegments {

    public int countSegments(String s) {
        int state = 1;
        int wordCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' || s.charAt(i) == '\n' || s.charAt(i) == '\t') {
                state = 1;
            } else if (state == 1) {
                wordCount++;
                state = 0;
            }
        }
        return wordCount;
    }

    public static void main(String[] args) {
        System.out.print(new NumberOfSegments().countSegments("     hsg ja     "));
    }

}
