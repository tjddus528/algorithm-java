import java.util.*;
class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] visited;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // BFS
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{characterX, characterY});
        visited = new int[51][51];
        visited[characterX][characterY] = 1;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            if(now[0] == itemX && now[1] == itemY) 
                return visited[now[0]][now[1]]-1;
            for(int i=0; i<4; i++) {
                int[] next = new int[]{now[0]+dx[i], now[1]+dy[i]};
                if(next[0]<1 || next[1]<1 || next[0]>50 || next[1]>50) continue;
                if(visited[next[0]][next[1]] > 0) continue;
                if(check(rectangle, now[0]+0.5*dx[i], now[1]+0.5*dy[i])) {
                    visited[next[0]][next[1]] = visited[now[0]][now[1]] + 1;
                    q.offer(next);
                }
            }
        }
        return 0;
    }
    static boolean check(int[][] rectangle, double x, double y) {
        boolean isLine = false;
        for(int[] rect: rectangle) {
            int a = rect[0];
            int b = rect[1];
            int c = rect[2];
            int d = rect[3];
            if((x == a || x == c) && b <= y && y <= d) isLine = true;
            if((y == b || y == d) && a <= x && x <= c) isLine = true;
            if(a < x && x < c && b < y && y < d) return false;
        }
        return isLine;
    }
}