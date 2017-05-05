/*

Determine whether an integer is a palindrome. Do this without extra space.

 */
package math.easy;

/**
 * Created by poorvank on 05/05/17.
 */
public class IntegerPalindrome {

    public boolean isPalindrome(int x) {

        if(x<0) {
            return false;
        }

        //Calculate number of digits in an integer.
        int length = (int)(Math.log10(x)+1);

        if(length==1) {
            return true;
        }

        while (length>1) {
            int last = x%10;
            int first = x/(int) (Math.pow(10,length-1));
            if(last!=first) {
                return false;
            }
            x = x%(int) (Math.pow(10,length-1));
            x = x/10;
            length -=2;
        }

        return true;


    }

    public static void main(String[] args) {
        int a = 1234;
        System.out.println(new IntegerPalindrome().isPalindrome(a));
    }

}
