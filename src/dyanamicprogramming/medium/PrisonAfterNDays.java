/*

There are 8 prison cells in a row, and each cell is either occupied or vacant.

Each day, whether the cell is occupied or vacant changes according to the following rules:

If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
Otherwise, it becomes vacant.
(Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.)

We describe the current state of the prison in the following way: cells[i] == 1 if the i-th cell is occupied, else cells[i] == 0.

Given the initial state of the prison, return the state of the prison after N days (and N such changes described above.)



Example 1:

Input: cells = [0,1,0,1,1,0,0,1], N = 7
Output: [0,0,1,1,0,0,0,0]
Explanation:
The following table summarizes the state of the prison on each day:
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]

Example 2:

Input: cells = [1,0,0,1,0,0,1,0], N = 1000000000
Output: [0,0,1,1,1,1,1,0]


Note:

cells.length == 8
cells[i] is in {0, 1}
1 <= N <= 10^9

 */
package dyanamicprogramming.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 16/12/18.
 */
public class PrisonAfterNDays {

    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String,Integer> conditionDayMap = new HashMap<>();
        Map<Integer,String> dayConditionMap = new HashMap<>();
        conditionDayMap.put(Arrays.toString(cells),0);
        dayConditionMap.put(0,Arrays.toString(cells));
        int i=1;
        int[] newCells=new int[8];
        while(i<=N) {
            newCells = convertCell(cells);
            String s = Arrays.toString(newCells);
            if(conditionDayMap.containsKey(s)) {
                int diff = conditionDayMap.get(s)==0?i:i-conditionDayMap.get(s);
                int left = N-i;
                int index = left%diff;
                String ans = dayConditionMap.get(conditionDayMap.get(s)+index);
                int x=0;
                newCells = new int[8];
                for(char c : ans.toCharArray()) {
                    if(c=='0') {
                        newCells[x]=0;
                        x++;
                    } else if(c=='1') {
                        newCells[x]=1;
                        x++;
                    }
                }
                return newCells;
            }
            conditionDayMap.put(s,i);
            dayConditionMap.put(i,s);
            i++;
            cells=newCells;
        }

        return newCells;

    }

    private int[] convertCell(int[] cells) {

        int[] dup = new int[8];

        dup[0]=0;dup[7]=0;

        for(int i=1;i<7;i++) {
            dup[i]=(1)-(cells[i-1]^cells[i+1]);
        }

        return dup;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,0,1,0,1,0,0};
        System.out.println(Arrays.toString(new PrisonAfterNDays().prisonAfterNDays(nums,27)));
    }

}
