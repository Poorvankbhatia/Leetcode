package backtracking.medium;

import java.util.HashSet;
import java.util.Set;

public class MaximumInvitations {

    /*
       Concept : Maximum Bipartite graph
   */
    public int maximumInvitations(int[][] grid) {
        int m = grid.length; // boys
        int n = grid[0].length; // girls

        int[] girlFixed = new int[n];

        for (int i = 0; i < n; i++) {
            girlFixed[i] = -1;
        }

        int invitations = 0;

        for (int i = 0; i < m; i++) {
            Set<Integer> seenGirl = new HashSet<>();

            if (dfs(grid, i, seenGirl, girlFixed)) {
                invitations++;
            }
        }
        return invitations;
    }

    private boolean dfs(int[][] grid, int boy, Set<Integer> seenGirl, int[] girlFixed) {
        int m = grid.length; // boys
        int n = grid[0].length; // girls

        for (int i = 0; i < n; i++) {
            if (grid[boy][i] == 1 && !seenGirl.contains(i)) {
                seenGirl.add(i);
                if (girlFixed[i] == -1 || dfs(grid, girlFixed[i], seenGirl, girlFixed)) {
                    girlFixed[i] = boy;
                    return true;
                }
            }
        }
        return false;
    }

}

/*

hungarian algorithm

O(mnn)

If you are trying to invite a girl, no one else should try to invite the same girl.
If you've already invited a girl, no one else should be able to invite the same girl.
If you weren't able to invite a girl, it won't be possible for anyone else to invite the same girl as well.
Therefore in all the 3 scenarios above, there's no need to try inviting this particular girl again.
 Hence no need for seen.remove(i).

 */