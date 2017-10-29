/*

Your task is to design the basic function of Excel and implement the function of sum formula. Specifically, you need to implement the following functions:

Excel(int H, char W): This is the constructor. The inputs represents the height and width of the Excel form. H is a positive integer, range from 1 to 26.
It represents the height. W is a character range from 'A' to 'Z'. It represents that the width is the number of characters from 'A' to W. The Excel form
content is represented by a height * width 2D integer array C, it should be initialized to zero. You should assume that the first row of C starts from 1,
and the first column of C starts from 'A'.


void Set(int row, char column, int val): Change the value at C(row, column) to be val.


int Get(int row, char column): Return the value at C(row, column).


int Sum(int row, char column, List of Strings : numbers): This function calculate and set the value at C(row, column), where the value should be the sum
of cells represented by numbers. This function return the sum result at C(row, column). This sum formula should exist until this cell is overlapped by another
 value or another sum formula.

numbers is a list of strings that each string represent a cell or a range of cells. If the string represent a single cell, then it has the following format :
ColRow. For example, "F7" represents the cell at (7, F).

If the string represent a range of cells, then it has the following format : ColRow1:ColRow2. The range will always be a rectangle, and ColRow1 represent
the position of the top-left cell, and ColRow2 represents the position of the bottom-right cell.


Example 1:
Excel(3,"C");
// construct a 3*3 2D array with all zero.
//   A B C
// 1 0 0 0
// 2 0 0 0
// 3 0 0 0

Set(1, "A", 2);
// set C(1,"A") to be 2.
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 0

Sum(3, "C", ["A1", "A1:B2"]);
// set C(3,"C") to be the sum of value at C(1,"A") and the values sum of the rectangle range whose top-left cell is C(1,"A") and bottom-right cell is C(2,"B"). Return 4.
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 4

Set(2, "B", 2);
// set C(2,"B") to be 2. Note C(3, "C") should also be changed.
//   A B C
// 1 2 0 0
// 2 0 2 0
// 3 0 0 6
Note:
You could assume that there won't be any circular sum reference. For example, A1 = sum(B1) and B1 = sum(A1).
The test cases are using double-quotes to represent a character.
Please remember to RESET your class variables declared in class Excel, as static/class variables are persisted across multiple test cases. Please see here for more details.


 */
package design.hard;

/**
 * Created by poorvank.b on 04/07/17.
 */
public class Excel {


    Cell[][] excelArray;
    private class Cell {
        Integer val;
        String[] formula;
        boolean isNumber;
        public void setFormula(String[] formula) {
            this.formula = formula;
            isNumber = false;
        }
        public int calculateFormula() {
            int sum = 0;
            for (String s : formula) {
                int c1 = s.charAt(0) - 'A';
                if (s.length() > 3) {
                    int i = 1;
                    while (s.charAt(i) != ':') {
                        i++;
                    }
                    int r1 = Integer.parseInt(s.substring(1, i)) - 1;
                    int c2 = s.charAt(i + 1) - 'A';
                    int r2 = Integer.parseInt(s.substring(i + 2)) - 1;
                    for (i = r1; i <= r2; i++) {
                        for (int j = c1; j <= c2; j++) {
                            if (excelArray[i][j].isNumber) {
                                sum += excelArray[i][j].val;
                            } else {
                                sum += excelArray[i][j].calculateFormula();
                            }
                        }
                    }
                } else {
                    int r1 = Integer.parseInt(s.substring(1)) - 1;
                    if (excelArray[r1][c1].isNumber) {
                        sum += excelArray[r1][c1].val;
                    } else {
                        sum += excelArray[r1][c1].calculateFormula();
                    }
                }
            }

            val = sum;
            return val;
        }
        public void setNumber(int val) {
            this.val = val;
            isNumber = true;
        }
    }

    public Excel(int H, char W) {
        excelArray = new Cell[H][W - 'A' + 1];
        for (int i = 0; i < excelArray.length; i++) {
            for (int j = 0; j < excelArray[0].length; j++) {
                excelArray[i][j] = new Cell();
                excelArray[i][j].setNumber(0);
            }
        }
    }

    public void set(int r, char c, int v) {
        excelArray[r - 1][c - 'A'].setNumber(v);
    }

    public int get(int r, char c) {
        if (excelArray[r - 1][c - 'A'].isNumber) {
            return excelArray[r - 1][c - 'A'].val;
        } else {
            return excelArray[r - 1][c - 'A'].calculateFormula();
        }
    }

    public int sum(int r, char c, String[] strs) {
        excelArray[r - 1][c - 'A'].setFormula(strs);
        return excelArray[r - 1][c - 'A'].calculateFormula();
    }
}