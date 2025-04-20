import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import org.w3c.dom.ls.LSOutput;

public class Main {
  static final int MAX_NUM = 1000001;
  static int n;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    // dp[i] : 0 ~ i 까지 필요한 최소 K
    long[] dp = new long[n];
    Arrays.fill(dp, Long.MAX_VALUE);
    // 첫번째 돌은 0으로 초기화
    dp[0] = 0;
    // i -> j 모든 경우를 탐색 O(n^2)
    for(int i=0; i<n; i++) {
      for(int j=i+1; j<n; j++) {
        // i번째에서 시작할 수 없는 상태면 건너뛰기
        if(dp[i] == Long.MAX_VALUE) continue;

        // i번째에서 시작할 수 있는 상태라면 i->j 힘 구하기
        long F = (long)(j-i) * (1+ Math.abs(A[i]-A[j]));
        // (0->i) vs (i->j)
        long maxF = Math.max(dp[i], F);
        if(maxF < dp[j]) {
          dp[j] = maxF;
        }
      }
    }
    System.out.println(dp[n-1]);
  }
}
