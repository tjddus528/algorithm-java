import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, target;
    static int[][] arr;
    static String result;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        target = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        arr[N/2][N/2] = 1;
        snail(2, 0);
        print();
        System.out.println(result);
    }

    private static void snail(int num, int dir) {
        int x = N/2, y =N/2;
        result = (x+1)+" "+(y+1);
        int cnt = 1;
        while(num <= N*N) {
            // 2번마다 가야하는 칸 개수 ++
            for(int j=0; j<2; j++) {
                // 칸 개수만큼 이동하면서 숫자 채우기
                for (int i = 0; i < cnt; i++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    arr[nx][ny] = num;
                    if(target==num) result = (nx+1)+" "+(ny+1);

                    num++;
                    if(num > N*N) return;
                    x = nx;
                    y = ny;
                }
                // 칸 개수만큼 이동했으면 방향 바꿔서 한번더
                dir = (dir+1)%4;
            }
            cnt++;
        }
    }

    private static void print() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
}
