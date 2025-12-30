import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] martix;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{-1, 0, 1, 0};
    static int[][][] dustRate = {
            {{-1, 1}, {1, 1}, {-2, 0}, {2, 0}, {0, -2}, {-1, 0}, {1, 0}, {-1, -1}, {1, -1}, {0, -1}},
            {{-1,-1}, {-1,1}, {0, -2}, {0, 2}, {2, 0}, {0, -1}, {0, 1}, {1, -1}, {1, 1}, {1, 0}},
            {{-1, -1}, {1,-1}, {-2,0}, {2, 0}, {0, 2}, {-1, 0}, {1, 0}, {-1, 1}, {1, 1}, {0, 1}},
            {{1,-1}, {1, 1}, {0, -2}, {0, 2}, {-2, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {-1, 0}}
    };
    static double[] percent = {0.01, 0.01, 0.02, 0.02, 0.05, 0.07, 0.07, 0.1, 0.1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        martix = new int[N][N];
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                martix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 시작 위치는 정가운데 좌표
        int x = N/2;
        int y = N/2;
        // 2. 토네이도 방향을 찾는다.
        // 3. 그 방향으로 이동하면 이동칸(y)의 모래가 사방에 어떤 비율로 퍼지는지 계산하고 반영한다.
        int d = 0;
        int cnt = 1, answer = 0;
        while(cnt < N) {
            if(cnt % 2 == 1) {
                for(int i=0; i<cnt; i++) {y--; answer += calDust(0, x, y);}
                for(int i=0; i<cnt; i++) {x++; answer += calDust(1, x, y);}
            }
            else if (cnt % 2 == 0) {
                for(int i=0; i<cnt; i++) {y++; answer += calDust(2, x, y);}
                for(int i=0; i<cnt; i++) {x--; answer += calDust(3, x, y);}
            }
            if(cnt == N-1) for(int i=0; i<cnt; i++) {y--; answer += calDust(0, x, y);}
            cnt++;
        }
        // 5. 토네이도 방향에 따라서 다음 계산할 좌표로 이동한다.
        System.out.println(answer);

    }

    static int calDust(int d, int x, int y) {
        // 4. 이때, 격자 밖으로 이동하는 모래의 양을 누적 계산한다.
        int away = 0;
        int[][] dustInfo = dustRate[d];
        int origin = martix[x][y];
        martix[x][y] = 0;
        int total = 0;
        for(int i=0; i<9; i++) {
            int tx = x + dustInfo[i][0];
            int ty = y + dustInfo[i][1];
            int res = (int) (origin * percent[i]);
            total += res;
            if(tx < 0 || ty < 0 || tx > N-1 || ty > N-1) {
                away += res;
                continue;
            }
            martix[tx][ty] += res;
        }

        int tx = x+dustInfo[9][0];
        int ty = y+dustInfo[9][1];
        if(tx < 0 || ty < 0 || tx > N-1 || ty > N-1) away += (origin - total);
        else  martix[tx][ty] += (origin - total);
        return away;
    }

    static void print() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(martix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
