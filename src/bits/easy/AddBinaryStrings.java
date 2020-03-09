/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 */
package bits.easy;

public class AddBinaryStrings {
    public String addBinary(String a, String b) {
        return util(a,b,2);
    }

    private String util(String a,String b,int base) {
        StringBuilder sb = new StringBuilder();

        int i=a.length()-1;
        int j=b.length()-1;

        int carry = 0;

        while(i>=0 || j>=0){
            int sum=0;

            if(i>=0 && a.charAt(i)=='1'){
                sum++;
            }

            if(j>=0 && b.charAt(j)=='1'){
                sum++;
            }

            sum += carry;

            if(sum>=base){
                carry=1;
            }else{
                carry=0;
            }

            sb.insert(0,  (char) ((sum%base) + '0'));

            i--;
            j--;
        }

        if(carry==1)
            sb.insert(0, '1');

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new AddBinaryStrings().addBinary("101","100"));
    }

}
