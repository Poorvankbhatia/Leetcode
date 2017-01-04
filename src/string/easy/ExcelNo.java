/*

Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28

 */
package string.easy;

/**
 * Created by poorvank on 04/01/17.
 */
public class ExcelNo {

    public int titleToNumber(String s) {

        int result =0;
        int i = s.length()-1;
        int t = 0;

        /*

         Starting from the right-hand side, the converted value for each character is the mapping integer * 26 to the t-th power,
         where t starts from 0.

         */
        while (i>=0) {

            int pow = (int) (Math.pow(26,t));
            //If used (s.charAt(i)-'A') then for A it will calculate 0;
            result += (pow)*(s.charAt(i)-'A'+1);
            i--;
            t++;

        }

        return result;

    }


    public static void main(String[] args) {
        System.out.print(new ExcelNo().titleToNumber("BA"));
    }

}
