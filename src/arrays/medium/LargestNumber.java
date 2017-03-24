/*

Given a list of non negative integers, arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.

Note: The result may be very large, so you need to return a string instead of an integer.

 */


package arrays.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by poorvank.b on 23/03/17.
 */

public class LargestNumber {

    private class IntegerComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer a,Integer b) {
            Long val1 = Long.parseLong(a+""+b);
            Long val2 = Long.parseLong(b+""+a);
            return val2.compareTo(val1);
        }

    }

    public String largestNumber(int[] nums) {

        if(nums==null || nums.length==0) {
            return "";
        }

        Integer[] newArray = new Integer[nums.length];
        int i = 0;
        for (int value : nums) {
            newArray[i++] = value;
        }

        Arrays.sort(newArray,new IntegerComparator());

        System.out.println(Arrays.toString(newArray));

        if(newArray[0]==0) {
            return "0";
        }

        StringBuilder stringBuilder = new StringBuilder();

        for (Integer num : newArray) {
            stringBuilder.append(num);
        }

        return stringBuilder.toString();

    }

    public static void main(String[] args) {

        int[] arr = new int[]{0,0,230};
        System.out.print(new LargestNumber().largestNumber(arr));

    }

}
