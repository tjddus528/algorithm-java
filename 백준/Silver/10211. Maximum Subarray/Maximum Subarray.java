import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while(t --> 0) {
      int n = Integer.parseInt(br.readLine());
      int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      int[][] dp = new int[n + 1][n + 1];
      int answer = arr[0];
      for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
          if (i <= j) {
            dp[i][j] = dp[i][j - 1] + arr[j - 1];
            answer = Math.max(answer, dp[i][j]);
          }
        }
      }
      System.out.println(answer);
    }
  }
}
