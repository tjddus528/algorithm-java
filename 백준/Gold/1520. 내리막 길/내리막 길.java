import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int cnt;
    static class Node implements Comparable<Node> {
        int x, y;
        int height;
        Node(int _x, int _y, int h) {
            this.x = _x;
            this.y = _y;
            this.height = h;
        }
        @Override
        public int compareTo(Node node) {
            return node.height - this.height;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] visited = new int[N][M];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0, map[0][0]));
        visited[0][0] = 1;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.x == N-1 && cur.y == M-1) continue;
            if(cur.height <= map[N-1][M-1]) continue;

            for(int d=0; d<4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if(nx<0 || ny<0 || nx>N-1 || ny>M-1) continue;
                if(cur.height <= map[nx][ny]) continue;
                if(visited[nx][ny] == 0)
                    pq.add(new Node(nx, ny, map[nx][ny]));
                visited[nx][ny] += visited[cur.x][cur.y];
            }
        }

        System.out.println(visited[N-1][M-1]);
    }
    static void dfs(int x, int y, boolean[][] visited) {
        if(x == N-1 && y == M-1) {
            cnt++;
            return;
        }
        visited[x][y] = true;
        for(int d=0; d<4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if(nx<0 || ny<0 || nx>N-1 || ny>M-1) continue;
            if(map[x][y] <= map[N-1][M-1]) continue;
            if(map[x][y] <= map[nx][ny]) continue;
            if(visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(nx, ny, visited);
            visited[nx][ny] = false;
        }
    }

}
