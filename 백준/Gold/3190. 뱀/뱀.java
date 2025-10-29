import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, K, L;
    static int[][] board;
    static class DIR {
        int t;
        String dir;
        DIR(int t, String dir) {
            this.t = t;
            this.dir = dir;
        }
    }
    static class Snake {
        int x;
        int y;
        int d;
        Snake(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    // 하 좌 상 우
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N+1][N+1];
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = 2;    // apple
        }
        L = Integer.parseInt(br.readLine());
        ArrayDeque<DIR> dirInfo = new ArrayDeque<>();
        for(int i=0; i<L; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            String d = st.nextToken();
            dirInfo.add(new DIR(t, d));
        }

        ArrayDeque<Snake> q = new ArrayDeque<>();
        q.offer(new Snake(1, 1, 3));
        board[1][1] = 1;
        int time = 0;
        while(!q.isEmpty()) {
//            print();
            Snake head = q.peekFirst();
            time++;
            int nx = head.x + dx[head.d];
            int ny = head.y + dy[head.d];
            int d = head.d;
            if(nx<1 || ny<1 || nx>N || ny>N) break;     // 범위 벗어나면
            if(board[nx][ny]==1) break;                 // 자기 자신을 만나면
            // 해당 시간에서 방향이 전환되는지 확인
            if(!dirInfo.isEmpty() && dirInfo.peek().t <= time) {
                d = chandDir(d, dirInfo.peek().dir);
                dirInfo.poll();
            }
            // 앞으로 전진
            q.addFirst(new Snake(nx, ny, d));
            if(board[nx][ny] == 2) {
                board[nx][ny] = 1;
                continue;
            }
            board[nx][ny] = 1;
            if(!q.isEmpty()) {
                Snake remove = q.pollLast();
                board[remove.x][remove.y] = 0;
            }
        }
        System.out.println(time);
    }
    static int chandDir(int d, String dir) {
        if(dir.equals("D")) {
            d = (d+1)%4;
        }
        else if(dir.equals("L")) {
            d -= 1;
            if(d<0) d=3;
        }
        return d;
    }

    static void print() {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
