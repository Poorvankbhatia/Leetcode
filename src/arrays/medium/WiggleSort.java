package arrays.medium;/*

Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].

 */

import java.util.Arrays;

/**
 * Created by poorvank on 20/08/16.
 */
public class WiggleSort {

    public static void main(String[] args) {

        int[] array =  new int[]{3, 5, 2, 1, 6, 4};

        for (int i=0;i<array.length;i++) {

            //Element at even position should be smaller than both its neighbours
            if((i&1)!=1) {
                if(i+1<array.length && array[i]>array[i+1]) {
                    swap(i,i+1,array);
                }
            } //element at odd position should be greater than both its neighbours
            else {
                if(i+1<array.length && array[i]<array[i+1]) {
                    swap(i,i+1,array);
                }
            }

        }

        System.out.println(Arrays.toString(array));

    }

    private static void swap(int i,int j,int[] array) {
        Integer temp = array[i];
        array[i]=array[j];
        array[j]=temp;
    }

}
