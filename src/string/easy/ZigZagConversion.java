/*

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of
rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);

 */

package string.easy;

/**
 * Created by poorvank on 27/11/16.
 */
public class ZigZagConversion {

    public String convert(String s, int numRows) {

        if(numRows<=1 || s.length()==0 || numRows>=s.length()) {
            return s;
        }

        String[] strings = new String[numRows];
        boolean up=false,down=false;

        int row=0,k=0;

        while (k<s.length()) {

            if(strings[row]!=null) {
                strings[row] += s.charAt(k);
            } else {
                strings[row] = Character.toString(s.charAt(k));
            }

            if(row==numRows-1) {
                up = true;
                down = false;

            }else if(row==0) {
                down = true;
                up = false;
            }

            if(down) {
                row++;
            }

            if(up) {
                row--;
            }

            k++;

        }


        StringBuilder sb =new StringBuilder();

        for (String str : strings) {
            sb.append(str);
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        System.out.print(new ZigZagConversion().convert("AB",1));
    }

}
