class Solution {
    static boolean[] visited;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for(int i=0; i<n; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            dfs(i, n, computers);
            answer++;
        }
        return answer;
    }
    static void dfs(int i, int n, int[][] computers) {
        if(i >= n) return;
        for(int j=0; j<n; j++) {
            if(i == j) continue;
            if(computers[i][j]==0) continue;
            if(visited[j]) continue;
            visited[j] = true;
            dfs(j, n, computers);
        }
    }
}