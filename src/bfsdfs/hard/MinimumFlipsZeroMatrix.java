package bfsdfs.hard;

import java.util.*;

public class MinimumFlipsZeroMatrix {
    private int[][] dir = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
    Set<String> set;
    public int minFlips(int[][] mat) {
        int m = mat.length;
        if(m==0) {
            return 0;
        }
        int n = mat[0].length;
        StringBuilder result = new StringBuilder();
        for (int i=0;i<m*n;i++) {
            result.append("0");
        }
        set = new HashSet<>();
        String hash = hash(mat);
        Queue<String> queue = new LinkedList<>();
        queue.add(hash);
        set.add(hash);
        int ans=0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-->0) {
                String poll = queue.poll();
                if(poll.equals(result.toString())) {
                    return ans;
                }
                for (int i=0;i<poll.length();i++) {
                    int x = i/n;
                    int y = i%n;
                    Set<Integer> change = new HashSet<>(); // Set indicating which indexes to change.
                    change.add(i);
                    for (int k=0;k<4;k++) {
                        int nextX = x+dir[k][0];
                        int nextY = y+dir[k][1];
                        if(nextX>=0 && nextY>=0 && nextX<m && nextY<n) {
                            change.add(nextX*n+nextY);
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int j=0;j<poll.length();j++) {
                        sb.append(!change.contains(j)?poll.charAt(j):(poll.charAt(j)=='1'?'0':'1'));
                    }
                    if(set.add(sb.toString())) {
                        queue.add(sb.toString());
                    }
                }
            }
            ans++;
        }
        return -1;
    }


    private String hash(int[][] mat) {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : mat) {
            for (int j = 0; j < mat[0].length; j++) {
                sb.append(ints[j]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{
                {1,1,1},
                {1,0,1},
                {0,0,0}
        };
        System.out.println(new MinimumFlipsZeroMatrix().minFlips(a));
    }

}
