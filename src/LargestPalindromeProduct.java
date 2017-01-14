/**
 * Created by poorvank.b on 04/01/17.
 */
public class LargestPalindromeProduct {

    public int largestPalindrome(int n) {

        if(n==0) {
            return 0;
        }

        int max = 100000001;

        int maxValue = ((int) (Math.pow(10,n)))-1;
        int minValue = (int) (Math.pow(10,n-1));

        for (int i=maxValue;i>=minValue;i--) {

            for (int j=maxValue;j>=minValue;j--) {



            }

        }

        return max;

    }

}
