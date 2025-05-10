import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] arr;
    static int[] target;
    static int[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        n = Integer.parseInt(data[0]);
        m = Integer.parseInt(data[1]);
        arr = new int[n][m];
        visited = new int[n][m];
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j]==2) target = new int[]{i,j};
            }
        }
        bfs(target[0], target[1]);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if (arr[i][j]==0) {
                    sb.append(0).append(" ");
                    continue;
                }
                if (visited[i][j] > 0) {
                    sb.append(visited[i][j]-1).append(" ");
                }
                else {
                    sb.append(-1).append(" ");
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static void bfs(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{x, y});
        visited[x][y] = 1;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            for(int i=0; i<4; i++) {
                int nx = now[0]+dx[i], ny = now[1]+dy[i];
                if (nx < 0 || ny < 0 || nx > n - 1 || ny > m - 1)
                    continue;
                if (visited[nx][ny] > 0) continue;
                if (arr[nx][ny] == 0) continue;
                visited[nx][ny] = visited[now[0]][now[1]] + 1;
                q.add(new int[]{nx, ny});
            }
        }
    }
}
