import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int n = Integer.parseInt(data[0]);
    int m = Integer.parseInt(data[1]);
    int[][] maze = new int[n+1][m+1];
    for(int i=1; i<=n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=1; j<=m; j++) {
        maze[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    int[][] dp = new int[n+1][m+1];
    for(int i=1; i<=n; i++) {
      for(int j=1; j<=m; j++) {
        dp[i][j] = maze[i][j] + Math.max(Math.max(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
      }
    }
    System.out.println(dp[n][m]);

  }
}
