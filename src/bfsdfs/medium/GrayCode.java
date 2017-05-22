/*

The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2

 */

package bfsdfs.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 22/05/17.
 */

//Currently not working as leetcode is able to judge based on one instance of gray code sequence

public class GrayCode {

    public List<Integer> grayCode(int n) {

        List<String> result = new ArrayList<>();
        if(n<=0) {
            return new ArrayList<>();
        }

        result.add("0");
        result.add("1");

        for (int i=2;i<(1<<n);i=i<<1) {

            for (int j=result.size()-1;j>=0;j--) {
                result.add(result.get(j));
            }

            for (int j=0;j<i;j++) {
                result.set(j,result.get(j)+"0");
            }

            for (int j=i;j<2*i;j++) {
                result.set(j,result.get(j)+"1");
            }

        }

        List<Integer> finalResult = new ArrayList<>();

        for (String s : result) {
            finalResult.add(Integer.parseInt(s,2));
        }


        return finalResult;

    }

    public static void main(String[] args) {
        System.out.println(new GrayCode().grayCode(3));
    }

}
