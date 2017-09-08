/*
Implement an iterator to flatten a 2d vector.
For example,
Given 2d vector =
[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
 */
package arrays.medium;

import java.util.List;

/**
 * Created by poorvank.b on 07/09/17.
 */
public class Vector2D {

    private List<List<Integer>> vec2d;
    private int rowId;
    private int colId;
    private int numRows;

    public Vector2D(List<List<Integer>> vec2d) {
        this.vec2d = vec2d;
        rowId = 0;
        colId = 0;
        numRows = vec2d.size();
    }

    public int next() {
        int ans = 0;
        if (colId < vec2d.get(rowId).size()) {
            ans = vec2d.get(rowId).get(colId);
        }
        colId++;
        if (colId == vec2d.get(rowId).size()) {
            colId = 0;
            rowId++;
        }
        return ans;
    }

    public boolean hasNext() {
        while (rowId < numRows && (vec2d.get(rowId) == null || vec2d.get(rowId).isEmpty())) {
            rowId++;
        }
        return vec2d != null && !vec2d.isEmpty() && rowId < numRows;
    }
}
