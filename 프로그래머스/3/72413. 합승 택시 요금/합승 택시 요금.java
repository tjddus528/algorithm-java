class Solution {
    static int[][] cost;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int INF = Integer.MAX_VALUE;
        System.out.println(INF);
        System.out.println(INF+1);
        cost = new int[n+1][n+1];
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                cost[i][j] = 1_000_000_000;
            }
        }
        for(int[] fare: fares) {
            cost[fare[0]][fare[1]] = fare[2];
            cost[fare[1]][fare[0]] = fare[2];
        }
        for(int i=1; i<=n; i++) cost[i][i] = 0;
        
        // 플로이드 워셜
        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k]+cost[k][j]);
                }
            }
        }
    
        int answer = cost[s][a] + cost[s][b];
        for(int k=1; k<=n; k++) {
            int c = cost[s][k] + cost[k][a] + cost[k][b];
            if(c < 0) continue;
            answer = Math.min(answer, cost[s][k] + cost[k][a] + cost[k][b]);
        }
        return answer;
    }
}