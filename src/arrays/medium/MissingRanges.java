/*

Given a sorted integer array where the range of elements are [0, 99] inclusive,
return its missing ranges. For example, given [0, 1, 3, 50, 75], return [“2”, “4->49”, “51->74”, “76->99”]

 */
package arrays.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 01/06/17.
 */
public class MissingRanges {

    public List<String> findMissingRanges(int[] vals) {

        List<String> list = new ArrayList<>();

        if(vals.length==0) {
            list.add("0->99");
            return list;
        }

        int i=0,j=0;

        if (vals[i] > 0) {
            addToList(list,0,vals[i]-1);
        }

        int n = vals.length;
        while (i<n) {

            j = i+1;
            if(j<n && vals[j]-vals[i]>=2) {
                int start = vals[i]+1;
                int end = vals[j]-1;
                addToList(list,start,end);
            }
            i=j;

        }

        if(vals[n-1]<99) {
            int start = vals[n-1]+1;
            addToList(list,start,99);
        }


        return list;

    }

    private void addToList(List<String> list,int start,int end) {
        if(end!=start) {
            list.add(start+"->"+end);
        } else {
            list.add(start+"");
        }
    }


    public static void main(String[] args) {
        int[] arr = {0};
        System.out.println(new MissingRanges().findMissingRanges(arr));
    }

}
