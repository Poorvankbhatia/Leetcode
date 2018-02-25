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
package design.medium;

import java.util.List;

/**
 * Created by poorvank.b on 31/12/17.
 */
public class Vector2D {

    private List<List<Integer>> vector2D;
    private int colId;
    private int rowId;
    private int rowCount;

    public Vector2D(List<List<Integer>> vector2D) {
        this.vector2D = vector2D;
        colId = 0;
        rowId = 0;
        rowCount = vector2D.size();
    }

    public int next() {
        int ans = 0;

        if(colId<vector2D.get(rowId).size()) {
            ans = vector2D.get(rowId).get(colId);
        }

        colId++;

        if(colId>=vector2D.get(rowId).size()) {
            colId = 0;
            rowId++;
        }

        return ans;
    }

    public boolean hasNext() {
        while (rowId<rowCount && (vector2D.get(rowId)==null || vector2D.get(rowId).size()==0)) {
            rowCount++;
        }

        return vector2D!=null && !vector2D.isEmpty() && rowId<rowCount;
    }


}


/*

Using Iterators:

public class Vector2D {
    private Iterator<List<Integer>> outer;
    private Iterator<Integer> inner;

    public Vector2D(List<List<Integer>> vec2d) {
        outer = vec2d.iterator();
        inner = Collections.emptyIterator(); //inner = outer.iterator(); wrong: if outer is null, then exception
    }

    public int next() {
        return inner.next();
    }

    public boolean hasNext() {
        if (inner.hasNext()) {
            return true;
        }
        if (!outer.hasNext()) {
            return false;
        }
        inner = outer.next().iterator();
        while(!inner.hasNext() && outer.hasNext()) {
            inner = outer.next().iterator();
        }
        return inner.hasNext();
    }
}

 */