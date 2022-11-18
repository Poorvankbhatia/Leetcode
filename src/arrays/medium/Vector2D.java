/*

Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.

Implement the Vector2D class:

Vector2D(int[][] vec) initializes the object with the 2D vector vec.
next() returns the next element from the 2D vector and moves the pointer one step forward. You may assume that all the calls to next are valid.
hasNext() returns true if there are still some elements in the vector, and false otherwise.


Example 1:

Input
["Vector2D", "next", "next", "next", "hasNext", "hasNext", "next", "hasNext"]
[[[[1, 2], [3], [4]]], [], [], [], [], [], [], []]
Output
[null, 1, 2, 3, true, true, 4, false]

Explanation
Vector2D vector2D = new Vector2D([[1, 2], [3], [4]]);
vector2D.next();    // return 1
vector2D.next();    // return 2
vector2D.next();    // return 3
vector2D.hasNext(); // return True
vector2D.hasNext(); // return True
vector2D.next();    // return 4
vector2D.hasNext(); // return False

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

    int row;
    int col;
    int[][] v;
    public Vector2D(int[][] v) {
        row = 0;
        col = 0;
        this.v = v;
    }

    public int next() {
        if (hasNext()) {
            return v[row][col++];
        }
        return -1;
    }

    public boolean hasNext() {
        if (row == v.length) {
            return false;
        }
        while (col == v[row].length) {
            row++;
            col = 0;
            if (row == v.length) {
                return false;
            }
        }
        return true;
    }
}

/*

G  I

 */
