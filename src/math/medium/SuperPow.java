/*

Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.

Example1:

a = 2
b = [3]

Result: 8
Example2:

a = 2
b = [1,0]

Result: 1024

 */

package math.medium;

/**
 * Created by poorvank on 01/06/17.
 */
public class SuperPow {

    public int superPow(int a, int[] b) {
        return superPow(a, b, b.length, 1337);
    }

    private int superPow(int a, int[] b, int length, int k) {
        if (length == 1) {
            return powMod(a, b[0], k);
        }

        return powMod(superPow(a, b, length - 1, k), 10, k) * powMod(a, b[length - 1], k) % k;
    }


    private int powMod(int x, int y, int k) {
        x %= k;
        int pow = 1;
        for (int i = 0; i < y; i++) {
            pow = (pow * x) % k;
        }
        return pow;
    }

    public static void main(String[] args) {
        int a = 2147483647;
        int[] b = {2,0,0,5,2,2,3,5,2,1,1,9,9,8,1};
        System.out.println(new SuperPow().superPow(a,b));
    }

}

/*

ab % k = (a%k)(b%k)%k
Since the power here is an array, we'd better handle it digit by digit.
One observation:
a^1234567 % k = (a^1234560 % k) * (a^7 % k) % k = (a^123456 % k)^10 % k * (a^7 % k) % k
Looks complicated? Let me put it other way:
Suppose f(a, b) calculates a^b % k; Then translate above formula to using f :
f(a,1234567) = f(a, 1234560) * f(a, 7) % k = f(f(a, 123456),10) * f(a,7)%k;

Method causing wrong error:

 private int mod = 1337;

    public int superPow(int a, int[] b) {

        if(b.length==0 || a==0) {
            return 1;
        }

        int longInteger = 0;

        for (int i : b) {
            longInteger = (longInteger*10) + i;
            longInteger %= mod;
        }

        return util(a%mod,longInteger%mod,mod)%1337;

    }


    private int util(int a,int b,int mod) {

        if(b==0) {
            return 1;
        }
        int temp = util(a,b/2,mod);
        if(b%2==0) {
            return ((temp%mod)*(temp%mod))%mod;
        } else {
            return (a*(temp%mod)*(temp%mod))%mod;
        }

    }


 */