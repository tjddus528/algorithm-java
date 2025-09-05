import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int T, N, K;
    static int[][] arr;
    static List<int[]> starts;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int result;
    // 백트래킹 DFS
    static void dfs(int x, int y, int length, int prev, boolean kDone) {
        // 현재 까지의 등산로 길이가 최대인지 갱신하기
        // System.out.println(path);
        // System.out.println(x+", "+y);
        result = Math.max(result, length);
        // 현재 위치부터 상하좌우로 이동
        for(int d=0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx<0 || ny<0 || nx>N-1 || ny>N-1) continue;
            if(visited[nx][ny]) continue;
            if(arr[nx][ny] < prev) {
                visited[nx][ny] = true;
                dfs(nx, ny, length+1, arr[nx][ny], kDone);
                visited[nx][ny] = false;
            }
            // (이전에 깎은 적이 없으면) 1~k만큼 깎아서 이동하고, 그때에는 k깎음 여부 true로 해서 넘기기
            if(kDone) continue;
            for(int k=1; k<=K; k++) {
                if(arr[nx][ny]-k >= 0 && arr[nx][ny]-k < prev) {
                    visited[nx][ny] = true;
                    dfs(nx, ny, length+1, arr[nx][ny]-k, true);
                    // 다시 돌아오면 이동했던 위치의 방문을 false로 되돌리기
                    visited[nx][ny] = false;
                }
            }
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N][N];
            visited = new boolean[N][N];
            starts = new ArrayList<>();
            result = 0;
            int max = 0;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    max = Math.max(max, arr[i][j]);
                }
            }

            // 가장 높은 지형(시작할 수 있는 위치) 구하기
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(arr[i][j]==max) starts.add(new int[]{i, j});
                }
            }

            // 시작 위치에서 상하좌우로 이동 or 1~K만큼 깎아서 이동
            for(int[] p: starts) {
                // 방문배열 초기화
                for(int i=0; i<N; i++) 
                    Arrays.fill(visited[i], false);
                // DFS
                visited[p[0]][p[1]] = true;
                dfs(p[0], p[1], 1, arr[p[0]][p[1]],false);
                visited[p[0]][p[1]] = false;
            }
            
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

}
