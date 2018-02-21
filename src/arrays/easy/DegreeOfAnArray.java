package arrays.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 10/02/18.
 */
public class DegreeOfAnArray {

    public int findShortestSubArray(int[] nums) {

        if(nums==null || nums.length==0) {
            return 0;
        }

        int n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        Map<Integer,Integer> firstOccurrence = new HashMap<>();
        Map<Integer,Integer> lastOccurrence = new HashMap<>();
        int max=0;
        for (int i=0;i<n;i++) {
            int num = nums[i];
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (max < map.get(num)) {
                max = map.get(num);
            }
            if(!firstOccurrence.containsKey(num)) {
                firstOccurrence.put(num,i);
            } else {
                lastOccurrence.put(num,i);
            }
        }

        if(max==1) {
            return 1;
        }

        int i=0;
        int minVal = Integer.MAX_VALUE;
        while (i<n) {
            if(map.get(nums[i])==max) {
                minVal = Math.min(minVal,lastOccurrence.get(nums[i])-firstOccurrence.get(nums[i])+1);
            }
            i++;
        }

        return minVal;

    }

    public static void main(String[] args) {
        System.out.println(new DegreeOfAnArray().findShortestSubArray(new int[]{1,2,2,3,1}));
    }

}
