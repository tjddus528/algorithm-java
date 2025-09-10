import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 탈출
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] arr = new char[R][C];
        int sx, sy;

        int[][] time = new int[R][C];
        boolean[][] visited = new boolean[R][C];
        int INF = Integer.MAX_VALUE;
        for(int i=0; i<R; i++) {
            Arrays.fill(time[i], INF);
        }
        ArrayDeque<int[]> water = new ArrayDeque<>();
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for(int i=0; i<R; i++){ 
            String str = br.readLine();
            for(int j=0; j<C; j++) {
                arr[i][j] = str.charAt(j);
                if(arr[i][j] == 'S') {
                    q.add(new int[]{i, j, 1});
                    visited[i][j] = true;
                }
                if(arr[i][j] == '*') {
                    water.add(new int[]{i, j, 1});
                    time[i][j] = 1;
                }
            }
        }

        // BFS
        // 매 분마다 - 물 먼저 이동, 그리고 고슴도치
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        // int INF = Integer.MAX_VALUE;
        
        while(!water.isEmpty()) {
            int[] cur = water.poll();
            int x = cur[0];
            int y = cur[1];
            int t = cur[2];
            for(int d=0; d<4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if(nx<0 || ny<0 || nx>R-1 || ny>C-1) continue;
                if(arr[nx][ny] == 'D' || arr[nx][ny] == 'X') continue;
                if(time[nx][ny] != INF && time[nx][ny] >= 0) continue;
                time[nx][ny] = t + 1;
                water.add(new int[]{nx, ny, t+1});
            }
        }

        // 고슴도치
        int result = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int t = cur[2];
            if(arr[x][y] == 'D') {
                result = t;
                break;
            }

            for(int d=0; d<4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx<0 || ny<0 || nx>R-1 || ny>C-1) continue;
                if(arr[nx][ny] == 'X') continue;
                if(visited[nx][ny]) continue;
                if(time[nx][ny] <= t+1) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, t+1});
            }
        }

        System.out.println((result==0)?"KAKTUS":result-1);


    }
}
