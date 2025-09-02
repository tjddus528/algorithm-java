import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int K, W, H;
    static int[][] arr;
    static int[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] hx = {-2, -2, 2, 2, -1, -1, 1, 1};
    static int[] hy = {1, -1, 1, -1, 2, -2, 2, -2};
    static class Monkey {
        int x;
        int y;
        int cnt;
        public Monkey(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H][W];
        visited = new int[H][W][K+1];
        for(int i=0; i<H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayDeque<Monkey> q = new ArrayDeque<>();
        q.add(new Monkey(0, 0, 0));
        for(int i=0; i<H; i++) {
            for(int j=0; j<W; j++) {
                for(int _k=0; _k<=K; _k++) {
                    visited[i][j][_k] = -1;
                }
            }
        }
        visited[0][0][0] = 0;
        
        while(!q.isEmpty()) {
            Monkey cur = q.poll();
            if(cur.x == H-1 && cur.y == W-1) break;

            // 인접 칸으로 이동
            for(int d=0; d<4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if(nx<0 || ny<0 || nx>H-1 || ny>W-1) continue;
                if(arr[nx][ny]==1) continue;
                if(visited[nx][ny][cur.cnt] >= 0) continue;
                visited[nx][ny][cur.cnt] = visited[cur.x][cur.y][cur.cnt] + 1;
                q.offer(new Monkey(nx, ny, cur.cnt));
            }
            // cnt > 0 이면, 말의 움직임
            if(cur.cnt < K) {
                for(int h=0; h<8; h++) {
                    int nx = cur.x + hx[h];
                    int ny = cur.y + hy[h];
                    if(nx<0 || ny<0 || nx>H-1 || ny>W-1) continue;
                    if(arr[nx][ny]==1) continue;
                    if(visited[nx][ny][cur.cnt+1] >= 0) continue;
                    visited[nx][ny][cur.cnt+1] = visited[cur.x][cur.y][cur.cnt] + 1;
                    q.offer(new Monkey(nx, ny, cur.cnt+1));
                }
            }
        }

        int max = -1;
        for(int i=0; i<=K; i++) {
            max = Math.max(max, visited[H-1][W-1][i]);
        }
        System.out.println(max==-1?-1:max);
        
    }  
}

// 2
// 8 12
// 0 0 0 0 0 0 0 0
// 0 1 1 1 1 1 1 0
// 0 1 1 1 1 1 1 0
// 0 1 1 0 0 0 0 0
// 0 1 1 0 1 1 1 1
// 0 1 1 0 1 1 1 1
// 0 1 1 0 0 0 0 0
// 0 1 1 0 1 1 1 0
// 0 1 1 1 1 1 1 0
// 1 1 0 0 0 0 1 1
// 1 1 1 1 1 1 1 1
// 1 1 1 0 1 1 0 0
// => 14


// 1
// 7 8
// 0 0 0 0 0 0 0
// 1 1 1 1 1 1 0
// 1 1 1 1 1 1 0
// 0 0 0 1 1 1 0
// 0 1 1 1 0 0 0
// 0 1 1 1 1 1 1
// 0 1 1 1 1 1 1
// 0 0 0 0 0 0 0
// => 25