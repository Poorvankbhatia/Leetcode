/*

The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.

 */
package string.easy;

/**
 * Created by poorvank on 17/11/16.
 */
public class CountAndSay {

    public String countAndSay(int n) {

        int i=1;
        String s = "1";
        while (i<n) {
            s = runLengthEncoding(s);
            i++;
        }

        return s;

    }


    private String runLengthEncoding(String str) {

        StringBuilder sb = new StringBuilder();

        for (int i=0;i<str.length();) {
            int count = 1;
            while (i!=str.length()-1 && str.charAt(i)==str.charAt(i+1)) {
                count++;
                i++;
            }
            sb.append(count).append(str.charAt(i));
            i++;
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        System.out.print(new CountAndSay().countAndSay(4));
    }

}
