import java.util.Scanner;
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[] dp = new int[5001];
    for(int i=0; i<=n; i++) dp[i]=5001;
    dp[3] = 1;
    dp[5] = 1;
    for(int i=6; i<=n; i++) {
      dp[i] = Math.min(dp[i], Math.min(dp[i-3]+1, dp[i-5]+1));
    }
    if (dp[n] > 5000)
      System.out.println(-1);
    else {
      System.out.println(dp[n]);
    }
  }
}
