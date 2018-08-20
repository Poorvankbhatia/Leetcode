package arrays.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank.b on 20/08/18.
 */
public class FairCandySwap {

    public int[] fairCandySwap(int[] A, int[] B) {

        int sumA = 0, sumB = 0;
        Set<Integer> setA = new HashSet<>();
        for (int aA : A) {
            sumA += aA;
            setA.add(aA);
        }
        for (int aB : B) sumB += aB;
        int dif = (sumA - sumB) / 2;
        for (int aB : B) {
            int targetA = aB + dif;
            if (setA.contains(targetA))
                return new int[]{targetA, aB};
        }
        return null;

    }


}

/*

time complexity: O(A + B)
space complexity: O(A)



 */