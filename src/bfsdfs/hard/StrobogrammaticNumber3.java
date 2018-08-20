/*

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:

Input: low = "50", high = "100"
Output: 3
Explanation: 69, 88, and 96 are three strobogrammatic numbers.
Note:
Because the range might be a large number, the low and high numbers are represented as string.

 */
package bfsdfs.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank.b on 31/07/18.
 */
public class StrobogrammaticNumber3 {

    public int strobogrammaticInRange(String low, String high){
        int count = 0;
        List<String> rst = new ArrayList<String>();
        for(int n = low.length(); n <= high.length(); n++){
            rst.addAll(helper(n, n));
        }
        for(String num : rst){

            if((num.length() == low.length()&&num.compareTo(low) < 0 ) ||(num.length() == high.length() && num.compareTo(high) > 0)) continue;
            count++;
        }
        return count;
    }

    private List<String> helper(int cur, int max){
        if(cur == 0) return new ArrayList<String>(Arrays.asList(""));
        if(cur == 1) return new ArrayList<String>(Arrays.asList("1", "8", "0"));

        List<String> rst = new ArrayList<String>();
        List<String> center = helper(cur - 2, max);

        for(int i = 0; i < center.size(); i++){
            String tmp = center.get(i);
            if(cur != max) rst.add("0" + tmp + "0");
            rst.add("1" + tmp + "1");
            rst.add("6" + tmp + "9");
            rst.add("8" + tmp + "8");
            rst.add("9" + tmp + "6");
        }
        return rst;
    }

}

/*

 The basic idea is to find generate a list of strobogrammatic number with the length between the length of lower bound and the
 length of upper bound.
  Then we pass the list and ignore the numbers with the same length of lower bound or upper bound but not in the range.

 */

