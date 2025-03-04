import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  static int n;
  static int[][] dp;
  static boolean[] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    n = Integer.parseInt(data[0]);
    int k = Integer.parseInt(data[1]);
    int[][] bags = new int[n+1][2];
    for(int i=1; i<=n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      bags[i][0] = Integer.parseInt(st.nextToken());
      bags[i][1] = Integer.parseInt(st.nextToken());
    }
    dp = new int[n+1][k+1];
    visited = new boolean[k+1];
    for(int w = 1; w <=k; w++) {
      for (int i = 1; i <= n; i++) {
        if (bags[i][0] > w) {
          dp[i][w] = dp[i - 1][w];
        } else {
          dp[i][w] = Math.max(dp[i - 1][w], bags[i][1] + dp[i - 1][w - bags[i][0]]);
        }
      }
    }
    System.out.println(dp[n][k]);
  }
}