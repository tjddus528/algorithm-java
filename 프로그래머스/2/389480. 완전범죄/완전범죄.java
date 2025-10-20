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

        // dp[i][j] = i번째 물건까지 고려했을 때, B가 흔적 개수에 따른 A의 최소 흔적 개수
        dp[0][0] = 0;
        for(int i=1; i<=len; i++) {
            // i번째 물건을 훔쳤을 때 A와 B가 남기는 흔적
            int a = info[i-1][0];
            int b = info[i-1][1];
            
            for(int j=0; j<m; j++) {
                // 1) A가 훔칠 경우 : i-1번째 물건을 고려했을 때, (A의 최소 흔적 + 현재 A 흔적)의 최소값
                dp[i][j] = Math.min(dp[i-1][j] + a, dp[i][j]);
                // 2) B가 훔칠 경우 : (i-1번째 물건을 고려했을 때, A의 최소 흔적)의 최소를 B가 j+b개의 흔적을 남기는 경우마다 갱신
                // B의 흔적 개수는 m을 초과할 수 없으므로, 초과하는 경우는 계산하지 않음
                if(j+b < m) 
                    dp[i][j+b] = Math.min(dp[i-1][j], dp[i][j+b]);
            }
        }
        
        // B의 모든 흔적 개수의 경우에 대해서 A의 최소 흔적 개수를 구함
        int answer = INF;
        for(int i=0; i<m; i++)
            answer = Math.min(answer, dp[len][i]);
        
        // 하지만 그 값이 n보다 크다면 어떤 경우에도 A,B가 모두 경찰에 걸리지 않고 모든 물건을 훔치는 경우는 없는 것임 
        return answer >= n ? -1 : answer;
    }
}