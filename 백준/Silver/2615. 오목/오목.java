import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] go = new int[21][21];
    static int[][][] memo = new int[21][21][4];
    // 아래, 오른쪽, 오른쪽 아래 대각선, 오른쪽 위 대각선
    static int[] dx = {1, 0, 1, -1};
    static int[] dy = {0, 1, 1, 1};

    static int win;
    static int startX, startY;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=1; i<=19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1; j<=19; j++) {
                go[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(findFive());
    }

    private static String findFive() {
        for(int j=1; j<=19; j++) {
            for(int i=1; i<=19; i++) {
                if(go[i][j] == 0) continue;
                for(int dir=0; dir<4; dir++) {
                    if(memo[i][j][dir] == 0 && conti(i, j, dir, go[i][j])==5) {
                        return go[i][j] + "\n" +i+" "+j;
                    }
                }
            }
        }
        return "0";
    }
    private static int conti(int x, int y, int dir, int color) {
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(go[nx][ny] == color) {
            return memo[nx][ny][dir] = conti(nx, ny, dir, color)+1;
        }
        return 1;
    }
}