/*
Given an integer n, return a list of all simplified fractions between 0 and 1 (exclusive)
such that the denominator is less-than-or-equal-to n. The fractions can be in any order.



Example 1:

Input: n = 2
Output: ["1/2"]
Explanation: "1/2" is the only unique fraction with a denominator less-than-or-equal-to 2.
Example 2:

Input: n = 3
Output: ["1/2","1/3","2/3"]
Example 3:

Input: n = 4
Output: ["1/2","1/3","1/4","2/3","3/4"]
Explanation: "2/4" is not a simplified fraction because it can be simplified to "1/2".
Example 4:

Input: n = 1
Output: []
 */
package math.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimplifiedFractions {

    public List<String> simplifiedFractions(int n) {
        List<Integer> num = new ArrayList<>();
        List<Integer> den = new ArrayList<>();
        for (int i=1;i<n;i++) {
            num.add(i);
            den.add(i+1);
        }

        Set<String> res = new HashSet<>();
        for (int x : num) {
            for (int y: den) {
                if(x<y) {
                    int gcd = generateGCD(x,y);
                    res.add(x/gcd+"/"+y/gcd);
                }
            }
        }

        return new ArrayList<>(res);
    }

    private int generateGCD(int a, int b) {

        if (b == 0) return a;
        else return generateGCD(b, a % b);

    }

    public static void main(String[] args) {
        System.out.println(new SimplifiedFractions().simplifiedFractions(5));
    }

}
