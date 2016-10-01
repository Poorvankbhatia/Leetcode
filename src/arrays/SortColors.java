package arrays;/*

Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent,
with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.



 */
import java.util.Arrays;

/**
 * Created by poorvank on 21/08/16.
 */
public class SortColors {

    public void sortColors(int[] nums) {
        int zeroCount =0;
        int oneCount = 0;
        int twoCount = 0;
        for (Integer num : nums) {
            if(num==0) {
                zeroCount++;
            } else if(num==1) {
                oneCount++;
            } else {
                twoCount++;
            }
        }

        int i=0;
        while (zeroCount != 0) {
            nums[i] = 0;
            zeroCount--;
            i++;
        }
        while (oneCount != 0) {
            nums[i] = 1;
            oneCount--;
            i++;
        }
        while (twoCount != 0) {
            nums[i] = 2;
            twoCount--;
            i++;
        }


    }

    public static void main(String[] args) {

        int[] nums = new int[]{0,1,2,0,1,2,0,2,2,1};
        SortColors sc= new SortColors();
        sc.sortColors(nums);
        System.out.println(Arrays.toString(nums));

    }

}
