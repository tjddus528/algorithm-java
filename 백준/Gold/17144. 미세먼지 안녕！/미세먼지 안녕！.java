import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int[][] matrix;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static List<int[]> air = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        matrix = new int[R][C];
        for(int i=0; i<R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == -1) {
                    air.add(new int[]{i, j});
                }
            }
        }

        while(T > 0) {
            spreadDust();
            circular();
            T--;
        }
        System.out.println(calDust());
    }

    // 1. 미세먼지 확산
    // - 인접한 네 방향, 공기청정기가 있거나 칸이 없으면 확산X
    // - 확산되는 양은 A/5, 남은 미세먼지 양은 A - (A/5) * (확산방향수)
    static void spreadDust() {
        int[][] temp = new int[R][C];
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(matrix[i][j]==-1) {
                    temp[i][j] = -1;
                    continue;
                }
                if(matrix[i][j]==0) continue;
                int origin = matrix[i][j];
                int spread = origin/5;
                int cnt = 0;
                for(int d=0; d<4; d++) {
                    int ni = i + dx[d];
                    int nj = j + dy[d];
                    if(ni<0 || nj<0 || ni>R-1 || nj>C-1) continue;
                    if(matrix[ni][nj]==-1) continue;
                    temp[ni][nj] += spread;
                    cnt++;
                }
                int remain = origin - spread*cnt;
                temp[i][j] += remain;
            }
        }

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                matrix[i][j] = temp[i][j];
            }
        }
    }

    // 2. 공기청정기 작동
    // - 위쪽은 반시계, 아래쪽은 시계방향
    // - 미세먼지가 바람의 방향대로 한 칸씩 이동
    // - 공기청정기로 들어간 미세먼지는 모두 정화
    static void circular() {
        // 위쪽
        int top = air.get(0)[0];
        for(int i=top-2; i>=0; i--) matrix[i+1][0] = matrix[i][0];
        for(int j=1; j<=C-1; j++) matrix[0][j-1] = matrix[0][j];
        for(int i=1; i<=top; i++) matrix[i-1][C-1] = matrix[i][C-1];
        for(int j=C-2; j>=1; j--) matrix[top][j+1] = matrix[top][j];
        matrix[top][1] = 0;

        // 아래쪽
        int bottom = air.get(1)[0];
        for(int i=bottom+2; i<R; i++) matrix[i-1][0] = matrix[i][0];
        for(int j=1; j<=C-1; j++) matrix[R-1][j-1] = matrix[R-1][j];
        for(int i=R-2; i>=bottom; i--) matrix[i+1][C-1] = matrix[i][C-1];
        for(int j=C-2; j>=1; j--) matrix[bottom][j+1] = matrix[bottom][j];
        matrix[bottom][1] = 0;

    }
    // 3. 남은 미세먼지 양 계산
    static int calDust() {
        int total = 0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(matrix[i][j]==-1) continue;
                total += matrix[i][j];
            }
        }
        return total;
    }

    static void print() {
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------");
    }

}
