import java.util.*;
class Solution {
    public int solution(int[][] info, int n, int m) {
        int INF = 100000;
        // info
        // 1 2 2
        // 2 3 1
        
        int len = info.length;
        int[][] dp = new int[len+1][m];
        for(int i=0; i<=len; i++) {
            Arrays.fill(dp[i], INF);
        }

        // dp[i][j] = A가 i번째 물건까지 훔쳤는데, B의 흔적이 j일 때의 A의 흔적
        dp[0][0] = 0;
        for(int i=1; i<=len; i++) {
            int a = info[i-1][0];
            int b = info[i-1][1];
            // i번째, A가 훔치거나 / B가 훔치거나
            for(int j=0; j<m; j++) {
                // 1) A가 훔칠 경우
                dp[i][j] = Math.min(dp[i-1][j] + a, dp[i][j]);
                // 2) B가 훔칠 경우
                if(j+b < m) 
                    dp[i][j+b] = Math.min(dp[i-1][j], dp[i][j+b]);
            }
        }
        
        int answer = INF;
        for(int i=0; i<m; i++)
            answer = Math.min(answer, dp[len][i]);
        
        return answer >= n ? -1 : answer;
    }
}