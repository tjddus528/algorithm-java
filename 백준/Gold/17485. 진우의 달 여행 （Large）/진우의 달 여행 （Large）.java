import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int MAX = 10000000;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int n = Integer.parseInt(data[0]);
    int m = Integer.parseInt(data[1]);
    int[][] arr = new int[n][m+2];
    int[][][] dp = new int[n][m+2][3];
    for(int i=0; i<n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=1; j<=m; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
        for(int k=0; k<3; k++) {
          dp[i][j][k] = MAX;
        }
      }
      for(int k=0; k<3; k++) {
        dp[i][0][k] = MAX;
        dp[i][m+1][k] = MAX;
      }
    }
    for(int j=1; j<m+1; j++)
      for(int k=0; k<3; k++)
        dp[0][j][k] = arr[0][j];

    for(int i=1; i<n; i++) {
      for (int j = 1; j <= m; j++) {
        dp[i][j][0] = Math.min(dp[i-1][j-1][1], dp[i-1][j-1][2]) + arr[i][j];  // 왼쪽 위
        dp[i][j][1] = Math.min(dp[i-1][j][0],   dp[i-1][j][2])   + arr[i][j];  // 위
        dp[i][j][2] = Math.min(dp[i-1][j+1][0], dp[i-1][j+1][1]) + arr[i][j];  // 오른쪽 위
      }
    }
    int result = Integer.MAX_VALUE;
    for(int j=1; j<=m; j++) {
      for(int k=0; k<3; k++)
        result = Math.min(result, dp[n-1][j][k]);
    }
    System.out.println(result);
  }
}
