import java.util.*;
class Solution {
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public int solution(int[][] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length;
        
        Deque<int[]> dq = new ArrayDeque<>();
        int[][] visited = new int[n][m];
        dq.offerLast(new int[]{0,0});
        visited[0][0] = 1;
        while(!dq.isEmpty()) {
            int[] now = dq.pollFirst();
            for(int i=0; i<4; i++) {
                int[] next = {now[0]+dx[i], now[1]+dy[i]};
                if(next[0] < 0 || next[1] < 0 || next[0] >= n || next[1] >= m) continue;
                if(maps[next[0]][next[1]]==0) continue;
                // System.out.println(next[0] + ", "+next[1]);
                
                if(visited[next[0]][next[1]] > 0) continue;
                visited[next[0]][next[1]] = visited[now[0]][now[1]]+1;
                
                if(next[0]==n-1 && next[1]==m-1) {
                    System.out.println(visited[n-1][m-1]);
                    return visited[n-1][m-1];
                };
                dq.offerLast(next);
                
            }
            
        }
        
        return -1;
    }
}