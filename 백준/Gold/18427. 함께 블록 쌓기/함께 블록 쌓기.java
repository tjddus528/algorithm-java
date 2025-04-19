import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  static final int DIVIDE = 10007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int n = Integer.parseInt(data[0]);
    int m = Integer.parseInt(data[1]);
    int h = Integer.parseInt(data[2]);
    List<List<Integer>> block = new ArrayList<>();
    for(int i=0; i<n; i++) {
      block.add(new ArrayList<>());
      String[] blck = br.readLine().split(" ");
      block.get(i).add(0);
      for(int j=0; j<blck.length; j++) {
        block.get(i).add(Integer.parseInt(blck[j]));
      }
    }

    long[][] dp = new long[n][h+1];
    for(int b: block.get(0)) {
      dp[0][b] = 1;
    }
    for(int i=1; i<n; i++) {
      for(int j=0; j<=h; j++) {
        long sum = 0;
        for(int k=0; k<block.get(i).size(); k++) {
          int height = block.get(i).get(k);
          if(j-height<0) continue;
          sum += dp[i-1][j-height];
        }
        dp[i][j] = sum % DIVIDE;
      }
    }
    System.out.println(dp[n-1][h] % DIVIDE);

  }
}
