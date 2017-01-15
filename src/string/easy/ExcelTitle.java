/*

Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB

 */
package string.easy;

/**
 * Created by poorvank on 05/01/17.
 */
public class ExcelTitle {

    public String convertToTitle(int n) {

        if(n<=0) {
            return "";
        }

        /*
        The key is n--. The minimum in 26-bit number is mapped to 1, not 0.
         */
        StringBuilder sb = new StringBuilder();

        while (n>0) {
            n--;
            char c = (char) ((n%26) + 'A');
            sb.append(c);
            n = n/26;
        }

        return sb.reverse().toString();

    }

}
