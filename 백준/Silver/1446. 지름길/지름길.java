import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, D;
    static int[][] path;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        D = Integer.parseInt(data[1]);
        path = new int[N][3];
        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            path[i][0] = Integer.parseInt(input[0]);
            path[i][1] = Integer.parseInt(input[1]);
            path[i][2] = Integer.parseInt(input[2]);
        }

        // 시작위치 작은순, 종료위치 작은 순, 지름길 길이 작은 순 정렬
        Arrays.sort(path, (a, b) -> {
            if(a[0]==b[0]) {
                if(a[1]==b[1]) return a[2]-b[2];
                return a[1]-b[1];
            }
            return a[0]-b[0];
        });

        // 가능한 지름길 선택해서 간 다음 백트래킹
        bt(0, 0, 0);
        System.out.println(result);

    }
    static void bt(int depth, int cur, int total) {
        if(depth == N) {
            result = Math.min(result, total+(D-cur));
            return;
        }
        int start = path[depth][0];
        int end   = path[depth][1];
        int dist  = path[depth][2];

        // 해당 지름길 선택 가능
        if(cur <= start && end <= D) {
            bt(depth+1, end, total+(start-cur)+Math.min(dist, end-start));
        }
        // 해당 지름길 못 감
        bt(depth+1, cur, total);
    }
}
