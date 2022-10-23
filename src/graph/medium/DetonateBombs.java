/*
You are given a list of bombs. The range of a bomb is defined as the area where its effect can be felt.
This area is in the shape of a circle with the center as the location of the bomb.

The bombs are represented by a 0-indexed 2D integer array bombs where bombs[i] = [xi, yi, ri]. xi and yi denote
the X-coordinate and Y-coordinate of the location of the ith bomb, whereas ri denotes the radius of its range.

You may choose to detonate a single bomb. When a bomb is detonated, it will detonate all bombs that lie in its range.
These bombs will further detonate the bombs that lie in their ranges.

Given the list of bombs, return the maximum number of bombs that can be detonated if you are allowed to detonate
only one bomb.

Input: bombs = [[2,1,3],[6,1,4]]
Output: 2
Explanation:
The above figure shows the positions and ranges of the 2 bombs.
If we detonate the left bomb, the right bomb will not be affected.
But if we detonate the right bomb, both bombs will be detonated.
So the maximum bombs that can be detonated is max(1, 2) = 2.

Input: bombs = [[1,1,5],[10,10,5]]
Output: 1
Explanation:
Detonating either bomb will not detonate the other bomb, so the maximum number of bombs that can be detonated is 1.

Input: bombs = [[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]
Output: 5
Explanation:
The best bomb to detonate is bomb 0 because:
- Bomb 0 detonates bombs 1 and 2. The red circle denotes the range of bomb 0.
- Bomb 2 detonates bomb 3. The blue circle denotes the range of bomb 2.
- Bomb 3 detonates bomb 4. The green circle denotes the range of bomb 3.
Thus all 5 bombs are detonated.


 */
package graph.medium;

public class DetonateBombs {

    public int maximumDetonation(int[][] bombs) {

        int ans = 0;
        for (int i = 0; i < bombs.length; i++) {
            boolean[] visited = new boolean[bombs.length];
            ans = Math.max(ans, dfs(i,bombs,visited));
        }
        return ans;
    }

    private int dfs(int current, int[][] bombs, boolean[] visited) {
        visited[current] = true;
        int ans = 0;
        for (int i=0;i<bombs.length;i++) {
            if(!visited[i] && isConnected(bombs[current],bombs[i])) {
                ans+=dfs(i,bombs,visited);
            }
        }
        return ans+1;
    }

    private boolean isConnected(int[] a,int[] b) {
        long x = a[0] - b[0], y = a[1] - b[1], r = a[2];
        return x * x + y * y <= r * r;
    }


    public static void main(String[] args) {
        int[][] a = new int[][] {
                {1,1,5},
                {5,1,1},
                {9,1,5}
        };
        System.out.println(new DetonateBombs().maximumDetonation(a));
    }


}

/*
In order to detonate the center point should be in the range of last circle.
Union Find cannot be used un this case, consider a small circle in range of two big circles.but
both big circles not in range of each other.union find gives 3
but correct answer is 2 .because a big circle can detonate a small
circle but small circle cant detonate the other big circle
 */
