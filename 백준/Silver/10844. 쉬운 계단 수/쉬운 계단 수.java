import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static final int DIV = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N + 1][10];
        for (int i = 1; i <= 9; i++) dp[1][i] = 1;
        for (int idx = 2; idx <= N; idx++) {
            dp[idx][0] = dp[idx - 1][1];
            dp[idx][9] = dp[idx - 1][8];
            for (int i = 1; i < 9; i++) {
                dp[idx][i] = (dp[idx - 1][i - 1] + dp[idx-1][i + 1]) % DIV;
            }
        }
        long result = 0;
        for (long d : dp[N])
            result = (result + d) % DIV;
        System.out.println(result % DIV);
    }
}
