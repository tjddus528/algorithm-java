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

        dp();

    }
    static void dp() {
        int[] d = new int[D+1];
        // d[i] = i까지 갈 때 필요한 거리의 최소
        // 초기화
        for(int i=0; i<=D; i++) {
            d[i] =i;
        }

        for(int j=1; j<=D; j++) {
            d[j] = Math.min(d[j], d[j-1]+1);
            for (int i = 0; i < N; i++) {
                int start = path[i][0];
                int end = path[i][1];
                int dist = path[i][2];
                if (j == end)
                    d[j] = Math.min(d[j], d[start] +dist);
            }
        }
        System.out.println(d[D]);
    }
}
