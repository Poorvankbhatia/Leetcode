/*
Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

 */
package dyanamicprogramming.medium;

import java.util.Arrays;

/**
 * Created by poorvank on 18/09/16.
 */
public class PerfectSquares {

    public int numSquares(int n) {
        if(n==0 || n==1) {
            return 1;
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<=n;i++) {
            if(isPerfectSquare(i)) {
                dp[i]=1;
            } else {
                for (int j=1;j<=i;j++) {
                    dp[i] = Math.min(dp[i-j]+dp[j],dp[i]);
                }
            }
        }
        return dp[n];
    }

    private boolean isPerfectSquare(int num) {
        int x = (int) Math.sqrt(num);
        return (x*x==num);
    }

    public static void main(String[] args) {
        System.out.println(new PerfectSquares().numSquares(9715));
    }

}

/*

Faster Sol:

Java BFS implementation: Start from node 0 in queue, and keep pushing in perfect square number + curr value, once we reach number n, we found the solution.

BFS:
public int numSquares(int n) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(0);
        visited.add(0);
        int depth = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            depth++;
            while(size-- > 0) {
                int u = q.poll();
                for(int i = 1; i*i <= n; i++) {
                    int v = u+i*i;
                    if(v == n) {
                        return depth;
                    }
                    if(v > n) {
                        break;
                    }
                    if(!visited.contains(v)) {
                        q.offer(v);
                        visited.add(v);
                    }
                }
            }
        }
        return depth;
    }


 */