import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] px;
    static int[] joy;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        px = new int[N+1];
        joy = new int[N+1];
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++)  px[i] = Integer.parseInt(st1.nextToken());
        for(int i=1; i<=N; i++) joy[i] = Integer.parseInt(st2.nextToken());

        // dp[i][j] : i번째 사람까지 왔을 때, 체력이 j일 때에 기쁨
        // dp[1][0] = 0, dp[1][1] ~ dp[1][99] = 20
        // dp[2][0] = 0, dp[2][1] ~ dp[2][20] = 20, dp[2][21] = 30, dp[2][22] ~ = 20+30=50
        // dp[3][0] = 0, dp[3][1] ~ dp[3][20] = 20, dp[3][21] = 30, dp[3][22] ~ dp[3][78] = 50
        // dp[3][79] = Math.max(dp[2][0]+25, dp[2][79]) = 50
        int[][] dp = new int[N+1][100];
        for(int i=1; i<=N; i++) {
            for(int j=0; j<100; j++) {
                if(j >= px[i]) dp[i][j] = Math.max(dp[i-1][j-px[i]]+joy[i], dp[i-1][j]);
                else dp[i][j] = dp[i-1][j];
            }
        }

        System.out.println(dp[N][99]);

    }
}
