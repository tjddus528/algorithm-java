import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] forest;
    static int[][] visited;       // 해당 위치까지 도달가능한 경로의 최댓값
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        forest = new int[N][N];
        visited = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                forest[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                answer = Math.max(answer, dfs(i, j));
        System.out.println(answer);

    }
    static int dfs(int x, int y) {
        if(visited[x][y] > 0) return visited[x][y];
        visited[x][y]=1;

        for(int d=0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx<0 || ny<0 || nx>N-1 || ny>N-1) continue;
            if(forest[nx][ny] <= forest[x][y]) continue;
            visited[x][y] = Math.max(visited[x][y], dfs(nx, ny)+1);
        }

        return visited[x][y];
    }

}
