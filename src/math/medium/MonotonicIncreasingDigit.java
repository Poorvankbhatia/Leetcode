/*

Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.

(Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)

Example 1:
Input: N = 10
Output: 9
Example 2:
Input: N = 1234
Output: 1234
Example 3:
Input: N = 332
Output: 299
Note: N is an integer in the range [0, 10^9].

 */
package math.medium;

/**
 * Created by poorvank.b on 03/12/17.
 */
public class MonotonicIncreasingDigit {

    public int monotoneIncreasingDigits(int N) {

        if(N==0) {
            return 0;
        }

        String s = Integer.toString(N);
        
        int m = s.length();
        long a[] = new long[m];

        for (int i=0; i<m; i++) {
            a[i] = (s.charAt(i) - '0');
        }


        long level = m-1;
        for (int i=m-1; i>0; i--)
        {
            if (a[i] < a[i-1])
            {
                a[i-1]--;
                level=i-1;
            }
        }

        StringBuilder sb = new StringBuilder();

        if (a[0] != 0)
        {
            for (int i=0; i<=level; i++)  {
                sb.append(a[i]);
            }
            for (long i=level+1; i<m; i++)  {
                sb.append(9);
            }
        }
        else
        {
            for (int i=1; i<level; i++) {
                sb.append(a[i]);
            }
            for (long i=level+1; i<m; i++)
                sb.append(9);
        }

        return Integer.parseInt(sb.toString());

    }

    public static void main(String[] args) {
        System.out.println(new MonotonicIncreasingDigit().monotoneIncreasingDigits(37282));
    }

}
