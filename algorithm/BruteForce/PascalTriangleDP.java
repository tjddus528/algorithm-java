package algorithm.BruteForce;

public class PascalTriangleDP {
  static int[][] dp;
  static int n;
  public static void main(String[] args) {
    n = 5;
    // init
    dp = new int[n][n];
    //    1
    //    1 1
    //    1 2 1
    //    1 3 3 1
    //    1 4 6 4 1
    for(int i=0; i<n; i++) {
      // 각 행의 첫번째와 마지막 값은 항상 1
      dp[i][0] = 1;
      dp[i][i] = 1;
      for(int j=1; j<i; j++)
        dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
    }

    // 4C2 = 4행 2열 = 6
    nCr(4, 2);
  }

  static void nCr(int n, int r) {
    System.out.println(dp[n][r]);
  }

}
