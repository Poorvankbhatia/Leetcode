package hashing.medium;

import java.util.HashMap;
import java.util.Map;

public class SmallestCommonElement {

    public int smallestCommonElement(int[][] mat) {
        int M = mat.length;
        int N = mat[0].length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] ints : mat) {
            if (map.containsKey(ints[0])) {
                map.put(ints[0], map.get(ints[0]) + 1);
            } else {
                map.put(ints[0], 1);
            }
            for (int j = 1; j < N; j++) {
                if (ints[j] != ints[j - 1])
                    if (map.containsKey(ints[j])) {
                        map.put(ints[j], map.get(ints[j]) + 1);
                    } else {
                        map.put(ints[j], 1);
                    }
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == M) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{
                {1,2,3},
                {2,3,4},
                {2,3,5}
        };
        System.out.println(new SmallestCommonElement().smallestCommonElement(mat));
    }

}

/*

-> Build a hashmap with all key as distinct elements of the first row. Mark value as 0.

-> For row = 1 to M-1
 For col = 0 to N-1
  If (mat[row][col] is already present in hashmap)
   If (Not present in current row.)
         hashmap.put(key,currentRowNo);

-> Iterate over hashmap and print keys for with value M

 */