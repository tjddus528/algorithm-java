import java.util.*;
class Solution {
    public long solution(int m, int n, int[][] puddles) {
        long[][] grid = new long[m+1][n+1];
        boolean[][] puddle = new boolean[m+1][n+1];
        for(int[] p: puddles) puddle[p[0]][p[1]] = true;
        grid[0][1] = 1;
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n ;j++) {
                if(puddle[i][j]) continue;
                grid[i][j] = (grid[i-1][j]+grid[i][j-1]) % 1_000_000_007;
            }
        }
        return grid[m][n];
    }
}