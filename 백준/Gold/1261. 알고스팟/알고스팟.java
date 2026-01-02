import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] matrix;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        matrix = new int[N+1][M+1];
        for(int i=1; i<N+1; i++) {
            String data = br.readLine();
            for(int j=1; j<M+1; j++) {
                matrix[i][j] = Integer.parseInt(String.valueOf(data.charAt(j-1)));
            }
        }

        boolean[][] visited = new boolean[N+1][M+1];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1, 1});
        visited[1][1] = true;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0] == N && cur[1] == M) {
                System.out.println(0);
                return;
            }
            for(int d=0; d<4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if(nx<=0 || ny<=0 || nx>N || ny>M) continue;
                if(matrix[nx][ny]==1) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }


        visited = new boolean[N+1][M+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);
        pq.add(new int[]{1, 1, 0});
        visited[1][1] = true;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[0] == N && cur[1] == M) {
                System.out.println(cur[2]);
                return;
            }
            for(int d=0; d<4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if(nx<=0 || ny<=0 || nx>N || ny>M) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if(matrix[nx][ny]==1) {
                    pq.add(new int[]{nx, ny, cur[2]+1});
                } else {
                    pq.add(new int[]{nx, ny, cur[2]});
                }
            }
        }
    }
}
