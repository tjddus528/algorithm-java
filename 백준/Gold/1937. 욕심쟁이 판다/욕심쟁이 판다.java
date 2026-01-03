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
    static class Node implements Comparable<Node>{
        int x, y;
        int leaf;
        Node(int _x, int _y, int _leaf) {
            this.x = _x;
            this.y = _y;
            this.leaf = _leaf;
        }
        @Override
        public int compareTo(Node node) {
            return this.leaf - node.leaf;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        forest = new int[N][N];
        visited = new int[N][N];
        int minX = 0, minY = 0;
        int minLeaf = Integer.MAX_VALUE;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                forest[i][j] = Integer.parseInt(st.nextToken());
                pq.add(new Node(i, j, forest[i][j]));
            }
        }

        int answer = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            for(int d=0; d<4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];
                if(nx<0 || ny<0 || nx>N-1 || ny>N-1) continue;
                if(forest[nx][ny] <= forest[cur.x][cur.y]) continue;
                if(visited[nx][ny] >= visited[cur.x][cur.y] + 1) continue;
                visited[nx][ny] = visited[cur.x][cur.y] + 1;
                pq.add(new Node(nx, ny, forest[nx][ny]));
                answer = Math.max(answer, visited[nx][ny]);
            }
        }
        System.out.println(answer+1);

    }

}
