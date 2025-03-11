import java.util.*;
class Solution {
    static int[][] check;
    public int solution(int n, int[][] results) {
        int answer = 0;
        check = new int[n+1][n+1];
        for(int[] result: results) {
            check[result[0]][result[1]] = 1;
            check[result[1]][result[0]] = -1;
        }
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(i==j || check[i][j] != 0) continue;

                    if(check[i][k] == 1 && check[k][j] == 1) {
                        check[i][j] = 1;
                        check[j][i] = -1;
                    }
                }
            }
        }
        for(int i=1; i<=n; i++) {
            int cnt = 0;
            for(int j=1; j<=n; j++) {
                if(i==j) continue;
                if(check[i][j]!=0) cnt++;
            }
            if(cnt==n-1) answer++;
        }
        return answer;
    }
}