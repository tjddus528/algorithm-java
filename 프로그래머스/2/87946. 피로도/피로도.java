class Solution {
    static int n;
    static boolean[] visited;
    static int answer;
    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        visited = new boolean[n];
        permutation(0, k, new int[n][2], dungeons);
        return answer;
    }
    static void permutation(int depth, int k, int[][] list, int[][] dungeons) {
        if(depth == n) {
            int cnt = 0;
            for(int[] l: list) {
                if(k >= l[0]) {
                    k-=l[1];
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
            return;
        }
        for(int i=0; i<n; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            list[depth][0] = dungeons[i][0];
            list[depth][1] = dungeons[i][1];
            permutation(depth+1, k, list, dungeons);
            visited[i] = false;
        }
    }
}