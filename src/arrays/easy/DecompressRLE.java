package arrays.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DecompressRLE {

    public int[] decompressRLElist(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int count=0;
        for(int j=nums.length-1;j>=1;j-=2) {
            map.put(j,nums[j-1]);
            count+=nums[j-1];
        }
        int[] res = new int[count];
        int k=0;
        int i=1;
        while (i<nums.length) {
            int x=0;
            while(x<map.get(i)) {
                res[k]=nums[i];
                k++;
                x++;
            }
            i+=2;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(new DecompressRLE().decompressRLElist(new int[]{73,95,5,68,6,14,98,3,98,39,100,69,76,77,93,46,91,69,26,13,30,53,15,53,34,17})));
    }

}
