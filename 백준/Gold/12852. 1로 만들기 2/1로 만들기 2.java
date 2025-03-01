import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  static int[] dp;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    dp = new int[n+1];
    List<List<Integer>> path = new ArrayList<>();
    for(int i=0; i<=n; i++) path.add(new ArrayList<>(10));
    for(int i=0; i<=n; i++) dp[i] = 1000001;
    dp[1] = 0;
    for(int i=2; i<=n; i++) {
      if(i%3==0) dp[i] = Math.min(dp[i], dp[i/3]+1);
      if(i%2==0) dp[i] = Math.min(dp[i], dp[i/2]+1);
      dp[i] = Math.min(dp[i], dp[i-1]+1);
    }
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    bw.write(dp[n]+"\n");
    int num = n;
    bw.write(num+" ");
    while(num > 1) {
      if(num%3==0 && dp[num/3]+1==dp[num]) num/=3;
      else if(num%2==0 && dp[num/2]+1==dp[num]) num/=2;
      else if(dp[num-1]+1==dp[num]) num-=1;
      bw.write(num+" ");
    }
    bw.flush();
    bw.close();
    br.close();
  }
}
