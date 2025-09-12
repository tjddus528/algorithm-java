import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T, N;
    static long min;
    static int[][] arr;
    static int[] output;
    static int allSubX, allSubY;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            min = Long.MAX_VALUE;
            arr = new int[N][2];
            output = new int[N/2];
            allSubX = 0;
            allSubY = 0;
            for(int i=0; i<N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());

                allSubX -= arr[i][0];
                allSubY -= arr[i][1];
            }

            combi(0, 0);
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void combi(int cnt, int idx) {
        if(cnt == N/2) {
            long vectorSum = calVector();
            min = Math.min(min, vectorSum);
            return;
        }

        for(int i=idx; i<N; i++) {
            output[cnt] = i;
            combi(cnt+1, i+1);
        }
    }

    static long calVector() {
        long plus[] = new long[2];
        long minus[] = new long[2];
        for(int i=0; i<N/2; i++) {
            int idx = output[i];
            plus[0] += arr[idx][0];
            plus[1] += arr[idx][1];
            minus[0] -= arr[idx][0];
            minus[1] -= arr[idx][1];
        }

        // 0에서 N/2개의 벡터를 모두 더한 값 + 0에서 나머지 벡터를 모두 뺀 값
        long resultX = plus[0] + (allSubX - minus[0]);
        long resultY = plus[1] + (allSubY - minus[1]);

        return resultX*resultX + resultY*resultY;
    }
}
