package hashing.medium;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by poorvank.b on 29/03/17.
 */
public class FindRightInterval {

    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }



    public int[] findRightInterval(Interval[] intervals) {

        int[] result = new int[intervals.length];

        if(intervals.length <= 1) {
            return new int[]{-1};
        }

        TreeMap<Integer,Integer> map = new TreeMap<>();

        for (int i=0;i<intervals.length;i++) {
            map.put(intervals[i].start,i);
        }

        for (int i=0;i<intervals.length;i++) {
            Integer key = map.ceilingKey(intervals[i].end);
            result[i] = key!=null ?map.get(key) : -1;
        }

        return result;

    }


    public static void main(String[] args) {

        Interval[] intervals = {new Interval(3,4),new Interval(2,3),new Interval(1,2)};
        System.out.print(Arrays.toString(new FindRightInterval().findRightInterval(intervals)));

    }

}

/*

[  [3,5],[2,3], [1,2],[3,4] ]

output should be : [-1,0,1,-1]

We get: [-1,3,1,-1]

 */