/*

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.



 */

package math.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 01/06/17.
 */
public class StrobogrammaticNumber {

    private Map<Character,Character> map;

    public boolean isStrobogrammatic(String num) {

        map = new HashMap<>();
        map.put('8','8');
        map.put('6','9');
        map.put('1','1');
        map.put('0','0');
        map.put('9','6');

        if(num.length()==0) {
            return true;
        }

        int n = num.length();


        int i=0,j=n-1;

        while (i<=j) {
            if(!map.containsKey(num.charAt(i)) || !map.containsKey(num.charAt(j))) {
                return false;
            } else {
                if(map.get(num.charAt(i))==num.charAt(j)) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            }
        }



        return true;

    }

    public static void main(String[] args) {
        System.out.println(new StrobogrammaticNumber().isStrobogrammatic("80108"));
    }

}
