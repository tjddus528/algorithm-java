import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int n = Integer.parseInt(br.readLine());
    int[] t = new int[n+2];
    int[] p = new int[n+2];
    for(int i=1; i<=n; i++) {
      st = new StringTokenizer(br.readLine());
      t[i] = Integer.parseInt(st.nextToken());
      p[i] = Integer.parseInt(st.nextToken());
    }
    // d[i] = i번째 상담을 했을 때, i일~퇴사날까지 얻는 최대 이익
    int[] dp = new int[n+2];
    for(int i=n; i>0; i--){
      if(t[i]+i > n+1) continue;
      // 상담이 가능하다면(퇴사 전까지 상담을 끝낼 수 있다면, 퇴사일 = n+1일째)
      dp[i] = p[i];
      int max = 0;
      for(int j=t[i]+i; j<=n; j++) max = Math.max(max, dp[j]);
      dp[i] += max;
    }
    int result = 0;
    for(int i=1; i<=n; i++) result = Math.max(result, dp[i]);
    System.out.println(result);
  }
}
