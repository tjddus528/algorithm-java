import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int test = Integer.parseInt(br.readLine());
    for (int t = 0; t < test; t++) {
      int n = Integer.parseInt(br.readLine());
      long[] dp = new long[Math.max(n + 1, 4)];
      dp[1] = 1;
      dp[2] = 2;
      dp[3] = 4;
      for (int i = 4; i <= n; i++) {
        dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1000000009;
      }
      System.out.println(dp[n]);
    }
  }
}
