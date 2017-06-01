/*

Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

 */
package arrays.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 01/06/17.
 */
public class SummaryRanges {

    public List<String> summaryRanges(int[] nums) {

        List<String> list = new ArrayList<>();
        if(nums.length==0) {
            return list;
        }

        int i=0,j=0;

        int n = nums.length;

        while (i<n) {

            int last = i;
            j = i+1;
            while (j<n) {
                if(nums[j]-nums[i]==1) {
                    j++;
                    i++;
                } else {
                    break;
                }
            }

            String s;
            if(last!=i) {
                s = nums[last]+"->"+nums[i];
            } else {
                s = nums[i]+"";
            }
            list.add(s);
            i++;

        }

        return list;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,4,5,7,9,10,11,12,17};
        System.out.println(new SummaryRanges().summaryRanges(nums));
    }

}
