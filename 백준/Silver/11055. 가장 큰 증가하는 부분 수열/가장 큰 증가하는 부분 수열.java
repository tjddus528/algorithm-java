import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  static int[] arr;
  static int[] dp;
  static int[] result;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    dp = new int[n];
    result = new int[n];
    dp[0] = arr[0];
    int result = dp[0];
    for(int i=1; i<n; i++) {
      dp[i] = arr[i];
      for(int j=i-1; j>=0; j--) {
        if(arr[j] < arr[i])
          dp[i] = Math.max(dp[j]+arr[i], dp[i]);
      }
      result = Math.max(dp[i], result);
    }
    System.out.println(result);
  }
}
