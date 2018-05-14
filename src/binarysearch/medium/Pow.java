/*

Implement pow(x, n).

 */
package binarysearch.medium;

/**
 * Created by poorvank on 13/10/16.
 */
public class Pow {

    public double myPow(double x, int n) {

        double temp;
        if( n == 0)
            return 1;
        temp = myPow(x, n/2);
        if (n%2 == 0)
            return temp*temp;
        else
        {
            if(n > 0)
                return x*temp*temp;
            else
                return (temp*temp)/x;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Pow().myPow(2,-5));
    }

}
