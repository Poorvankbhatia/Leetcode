/*

An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.



Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]


Constraints:

10 <= low <= high <= 10^9


 */
package backtracking.medium;

import java.util.ArrayList;
import java.util.List;

public class SequentialDigits {

    public List<Integer> sequentialDigits(int low, int high) {
        int lowDigitCount = (int)Math.floor(Math.log10(low) + 1);
        int highDigitCount =  (int)Math.floor(Math.log10(high) + 1);
        List<Integer> result = new ArrayList<>();
        for(int i=lowDigitCount;i<=highDigitCount;i++) {
            util(new StringBuilder(),result,1,i,0,low,high);
        }
        return result;
    }

    private void util(StringBuilder sb, List<Integer> list, int start, int count, int index,int low,int high) {
        if(index==count) {
            Integer x = Integer.parseInt(sb.toString());
            if(x>=low && x<=high) {
                list.add(x);
            }
            return;
        }
        if(start>9) {
            return;
        }
        if(index==0) {
            for(int i=start;i<9;i++) {
                sb.append(i);
                util(sb,list,i+1,count,index+1,low,high);
                sb.deleteCharAt(index);
            }
        } else {
            sb.append(start);
            util(sb,list,start+1,count,index+1,low,high);
            sb.deleteCharAt(index);
        }
    }

    public static void main(String[] args) {
        System.out.println(new SequentialDigits().sequentialDigits(10,1000000));
    }

}

/*

Another Sol:

 public List<Integer> sequentialDigits(int low, int high) {
        int[] allNums = {12,23,34,45,56,67,78,89,
                         123,234,345,456,567,678,789,
                         1234,2345,3456,4567,5678,6789,
                         12345,23456,34567,45678,56789,
                         123456,234567,345678,456789,
                         1234567,2345678,3456789,
                         12345678,23456789,
                         123456789};
        List<Integer> res = new ArrayList<>();
        int n = allNums.length;
        for (int i = 0; i < n; i++) {
            if (allNums[i] < low) continue;
            if (allNums[i] > high) break;
            res.add(allNums[i]);
        }
        return res;
    }

 */