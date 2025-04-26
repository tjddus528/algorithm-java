import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int n, m;
  static int[] arr;
  static boolean[][] dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    arr = new int[n+1];
    // dp[i][j] : i번째 추까지 사용했을 때, j 무게를 만들 수 있는지 여부
    // 추의 개수 <= 30, 추 무게 <= 500 : 추 무게의 총합 <= 15000
    dp = new boolean[n+1][150001];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=1; i<=n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    dfs(0, 0);
    StringBuilder sb = new StringBuilder();
    m = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<m; i++) {
      int w = Integer.parseInt(st.nextToken());
      if(w > 15000) {
        sb.append("N ");
        continue;
      }
      sb.append(dp[n][w]?"Y ":"N ");
    }
    System.out.println(sb);

  }

  // idx번째 추, 왼쪽 저울의 무게
  public static void dfs(int idx, int weight) {
    if(dp[idx][weight]) return;
    dp[idx][weight] = true;
    if(idx >= n) return;

    dfs(idx+1, weight);                        // 해당 idx 추를 올리지 않음
    dfs(idx+1, weight+arr[idx+1]);      // 해당 idx 추를 왼쪽에 올림
    dfs(idx+1, Math.abs(weight-arr[idx+1]));   // 해당 idx 추를 오른쪽에 올림
  }
}