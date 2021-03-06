/*

Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers.
Return the maximum product you can get.

For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).

Note: You may assume that n is not less than 2 and not larger than 58.

 */
package dyanamicprogramming.medium;

/**
 * Created by poorvank on 19/01/17.
 */
public class IntegerBreak {

    public int integerBreak(int n) {

        if(n<=2) {
            return 1;
        }

        int[] arr = new int[n+1];
        arr[1]=0;
        arr[2]=1;

        for(int i=3;i<=n;i++) {
            arr[i]=Integer.MIN_VALUE;
            for(int j=1;j<i;j++) {
                arr[i]=Math.max(arr[i],Math.max(arr[i-j]*j,(i-j)*j));
            }
        }

        return arr[n];

    }

    public static void main(String[] args) {
        System.out.println(new IntegerBreak().integerBreak(58));
    }

}


/*

If we see the breaking result for some numbers, we can see repeated pattern like the following:

2 -> 1*1
3 -> 1*2
4 -> 2*2
5 -> 3*2
6 -> 3*3
7 -> 3*4
8 -> 3*3*2
9 -> 3*3*3
10 -> 3*3*4
11 -> 3*3*3*2
We only need to find how many 3's we can get when n> 4. If n%3==1, we do not want 1 to be one of the broken numbers, we want 4.

public int integerBreak(int n) {

    if(n==2) {
            return 1;
        }
        if(n==3) {
            return 2;
        }
        if(n==4) {
            return 4;
        }


        if(n%3==0) {
            int m = n/3;
            return (int) Math.pow(3,m);
        } else if(n%3==2) {
            int m = (n-2)/3;
            return (int) Math.pow(3,m)*2;
        } else if(n%3==1) {
            int m = (n-4)/3;
            return (int) Math.pow(3,m)*4;
        }

        return -1;
}

Prove that for all integers n > 4, ( ( n-3 ) * 3 ) > n.
This gives reason to the 3, and why we may want to consider special cases for preceding numbers.



If we see some examples of this problems, we can easily observe following pattern.
The maximum product can be obtained be repeatedly cutting parts of size 3 while size is greater than 4,
keeping the last part as size of 2 or 3 or 4. For example, n = 10, the maximum product is obtained by 3, 3, 4. For n = 11,
the maximum product is obtained by 3, 3, 3, 2.


int maxProd(int n)
{
   // n equals to 2 or 3 must be handled explicitly
   if (n == 2 || n == 3) return (n-1);

   // Keep removing parts of size 3 while n is greater than 4
   int res = 1;
   while (n > 4)
   {
       n -= 3;
       res *= 3; // Keep multiplying 3 to res
   }
   return (n * res); // The last part multiplied by previous parts
}

 */
