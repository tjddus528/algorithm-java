import java.util.*;
class Solution {
    // 1번째 도둑 -> 마지막 X
    // 1번째 x -> 마지막 도둑
    public int solution(int[] money) {
        int answer = 0; 
        int n = money.length;
        int[] dp = new int[n];
        dp[0] = money[0];
        dp[1] = Math.max(money[0], money[1]);
        for(int i=2; i<n-1; i++) {
            dp[i] = Math.max(dp[i-2]+money[i], dp[i-1]);
        }
        // System.out.println(Arrays.toString(dp));
        int result = dp[n-2];
        dp[0] = 0;
        dp[1] = money[1];
        for(int i=2; i<n; i++) {
            dp[i] = Math.max(dp[i-2]+money[i], dp[i-1]);
        }
        // System.out.println(Arrays.toString(dp));
        return Math.max(result, dp[n-1]);
    }
}