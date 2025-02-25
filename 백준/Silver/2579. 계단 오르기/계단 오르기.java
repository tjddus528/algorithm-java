import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    // d[i] = N번째 계단~i번째 계단까지의 점수 최댓값
    // d[i] = max(d[i+2]+step[i], d[i+3]+step[i+1]+step[i])
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] step = new int[n+1];
    for(int i=1; i<=n; i++)
      step[i] = Integer.parseInt(br.readLine());

    if (n == 1) {
      System.out.println(step[1]);
      return;
    }
    int[] dp = new int[n+1];
    dp[n] = step[n];
    dp[n-1] = step[n] + step[n-1];
    dp[n-2] = step[n] + step[n-2];
    for(int i=n-3; i>0; i--) {
      dp[i] = Math.max(dp[i+2]+step[i], dp[i+3]+step[i+1]+step[i]);
    }
    System.out.println(Math.max(dp[1], dp[2]));

  }
}
