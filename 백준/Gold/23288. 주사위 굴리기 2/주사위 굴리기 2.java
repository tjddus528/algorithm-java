import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] map;
    static int[][] dice = {
            {0, 2, 0},
            {4, 1, 3},
            {0, 5, 0},
            {0, 6, 0}
    };
    //           d  =  0  1  2  3
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = 0, y = 0, d = 0;
        int score = 0;
        while(K > 0) {
            // 1. 주사위를 이동방향으로 한 칸 굴린다.
            int nx = x + dx[d];
            int ny = y + dy[d];
            // (이동 방향에 칸이 없다면, 이동 방향을 반대로)
            if(nx<0 || ny<0 || nx>N-1 || ny>M-1) {
                d = (d+2)%4;
                nx = x + dx[d];
                ny = y + dy[d];
            }
            rollDice(d);

            // 2. 도착한 칸 (x, y) 에 대한 점수 획득 (BFS)
            score += getScore(nx, ny);

            // 3. 주사위 아래면 A와 칸(x,y)의 B를 비교해 이동방향을 결정
            int A = dice[3][1];
            int B = map[nx][ny];
            d = getDirection(A, B, d);

            x = nx;
            y = ny;
            K--;
        }
        System.out.println(score);
    }

    static void rollDice(int d) {
        int temp = dice[3][1];
        switch (d) {
            case 0:
                dice[3][1] = dice[1][2];
                dice[1][2] = dice[1][1];
                dice[1][1] = dice[1][0];
                dice[1][0] = temp;
                break;
            case 1:
                dice[3][1] = dice[2][1];
                dice[2][1] = dice[1][1];
                dice[1][1] = dice[0][1];
                dice[0][1] = temp;
                break;
            case 2:
                dice[3][1] = dice[1][0];
                dice[1][0] = dice[1][1];
                dice[1][1] = dice[1][2];
                dice[1][2] = temp;
                break;
            case 3:
                dice[3][1] = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = temp;
                break;
        }
    }

    static int getScore(int x, int y) {
        int target = map[x][y];
        int cnt = 0;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        visited[x][y] = true;
        q.add(new int[]{x, y});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0||ny<0||nx>N-1||ny>M-1) continue;
                if(map[nx][ny] != target) continue;
                if(visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
        return cnt * target;
    }
    static int getDirection(int A, int B, int curD) {
        if(A>B) return (curD+1)%4;
        else if(A<B) {
            if(curD==0) return 3;
            return (curD-1)%4;
        }
        else return curD;
    }
}
