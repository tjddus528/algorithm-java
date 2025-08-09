import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N+1][2];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 정렬은 필수는 아님
//        Arrays.sort(arr, (o1, o2) -> o1[0]-o2[0]);
        int[][] dp = new int[N+1][K+1];
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=K; j++) {
                // 현재 물건의 무게가 j무게보다 커서 배낭에 넣을 수 없을 때
                if(arr[i][0] > j) dp[i][j] = dp[i-1][j];
                // 현재 물건의 무게가 j무게보다 작아서 배낭에 넣을 수 있을 때
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-arr[i][0]]+arr[i][1]);
                }
            }
        }
        System.out.println(dp[N][K]);


    }
}
