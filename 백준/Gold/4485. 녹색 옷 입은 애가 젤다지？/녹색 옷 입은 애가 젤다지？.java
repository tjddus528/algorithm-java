import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 녹색 옷 입은 애가 젤다지?
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N;
    static int[][] arr;
    static int[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 1;
        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N==0) break;
            arr = new int[N][N];
            visited = new int[N][N];
            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> o1[2]-o2[2]));
            pq.offer(new int[]{0, 0, arr[0][0]});
            for(int i=0; i<N; i++) 
                Arrays.fill(visited[i], INF);
            visited[0][0] = arr[0][0];

            while(!pq.isEmpty()) {
                int[] cur = pq.poll();
                int x = cur[0];
                int y = cur[1];
                if(x==N-1 && y==N-1) break;

                for(int d=0; d<4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if(nx<0 || ny<0 || nx>N-1 || ny>N-1) continue;
                    // if(visited[nx][ny] > 0) continue;
                    if(visited[x][y] + arr[nx][ny] < visited[nx][ny]) {
                        visited[nx][ny] = visited[x][y] + arr[nx][ny];
                        pq.offer(new int[]{nx, ny, visited[nx][ny]});
                    }
                }
            }

            System.out.println("Problem "+ (idx++) + ": "+ visited[N-1][N-1]);
        }
        

        
    }

}
