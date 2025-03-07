import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  static int L = 5001;
  public static void main(String[] args) throws IOException {
    long[] dp = new long[L+1];
    dp[0] = 1;
    dp[2] = 1;
    for(int i = 4; i<=L; i+=2){
      for(int j=0; j<i; j+=2) {
        dp[i] += dp[j]*dp[i-j-2];
        dp[i] %= 1_000_000_007;
      }
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while(t-->0) {
      int n = Integer.parseInt(br.readLine());
      System.out.println(dp[n]);
    }
  }
}
