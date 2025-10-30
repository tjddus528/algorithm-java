import java.util.*;
class Solution {
    static int n, m;
    static int gx, gy;
    static int sx, sy;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int[][] visited;
    public int solution(String[] board) {
        int answer = 0;
        n = board.length;
        m = board[0].length();
        visited = new int[n][m];
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<board[0].length(); j++) {
                if(board[i].charAt(j)=='G') {
                    gx = i;
                    gy = j;
                }
                if(board[i].charAt(j)=='R') {
                    sx = i;
                    sy = j;
                }
            }
        }
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sx, sy, 0});
        visited[sx][sy]=1;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(board[cur[0]].charAt(cur[1])=='G') {
                return cur[2];
            }
            for(int d=0; d<4; d++) {
                int x = cur[0];
                int y = cur[1];
                for(int k=0; k<Math.max(n, m); k++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if(nx<0 || ny<0 || nx>n-1 || ny>m-1) {
                        if(k>0) {
                            if(visited[x][y]==1) break;
                            q.offer(new int[]{x, y, cur[2]+1});
                            visited[x][y]=1;
                        }
                        break;
                    }
                    if(board[nx].charAt(ny)=='D') {
                        if(visited[x][y]==1) break;
                        q.offer(new int[]{x, y, cur[2]+1});
                        visited[x][y]=1;
                        break;
                    }
                    x = nx;
                    y = ny;
                }
            }
        }
        
        return -1;
    }
}