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
    // d[i] = i일~퇴사날까지 얻는 최대 이익
    // d[i] = max(i일 상담을 하고 그 다음 상담 가능한 날짜부터 상담했을 때 최대 이익
    //              vs i일 상담을 안하고, 다음 날부터의 최대 이익)
    int[] dp = new int[n+2];
    for(int i=n; i>0; i--){
      // 상담이 가능하다면(퇴사 전까지 상담을 끝낼 수 있다면), 퇴사일 = n+1일째
      if(t[i]+i <= n+1) {
        dp[i] = Math.max(p[i] + dp[t[i]+i], dp[i+1]);
      } else {
        dp[i] = dp[i+1];
      }
    }
    System.out.println(dp[1]);
  }
}
