import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int test = Integer.parseInt(br.readLine());
    for(int t=0; t<test; t++) {

    int n = Integer.parseInt(br.readLine());
    int[][] sticker = new int[2][n+1];
    for(int i=0; i<2; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=1; j<=n; j++) {
        sticker[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] dp = new int[2][n+1];
    dp[0][1] = sticker[0][1] + dp[1][0];
    dp[1][1] = sticker[1][1] + dp[0][0];

    for(int i=2; i<=n; i++) {
      dp[0][i] = sticker[0][i] + Math.max(dp[1][i-1], dp[1][i-2]);
      dp[1][i] = sticker[1][i] + Math.max(dp[0][i-1], dp[0][i-2]);
    }

    System.out.println(Math.max(dp[0][n], dp[1][n]));
    }

  }

}
