import java.util.*;
class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        int N = sticker.length;
        int[] dp = new int[N];
        // 0) 스티커 개수가 1 or 2개일 경우
        if(N==1) return sticker[0];
        if(N==2) return Math.max(sticker[0], sticker[1]);
        if(N==3) return Math.max(sticker[0], Math.max(sticker[1], sticker[2]));
        
        // 1) 첫번째 스티커 선택하는 경우
        dp[0] = sticker[0];
        dp[1] = dp[0];
        dp[2] = dp[1]+sticker[2];
        for(int i=3; i<N-1; i++) {
            dp[i] = Math.max(dp[i-2], dp[i-3]) + sticker[i];
            answer = Math.max(answer, dp[i]);
        }
        // 2) 첫번째 선택x, 마지막 스티커 선택하는 경우
        dp[0] = 0;
        dp[1] = sticker[1];
        dp[2] = sticker[2];
        for(int i=3; i<N; i++) {
            dp[i] = Math.max(dp[i-2], dp[i-3]) + sticker[i];
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }
}