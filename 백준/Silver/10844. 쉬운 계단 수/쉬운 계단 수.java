import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static final int DIV = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        long[][] dp = new long[N+1][10];
        for(int i=1; i<=9; i++) dp[0][i] = 1;
        for(int idx=0; idx<N; idx++) {
            for(int i=0; i<10; i++) {
                if(dp[idx][i] > 0) {
                    if(i-1>=0) {
                        dp[idx+1][i-1] += dp[idx][i];
                        dp[idx+1][i-1] %= DIV;
                    }
                    if(i+1<10) {
                        dp[idx+1][i+1] += dp[idx][i];
                        dp[idx+1][i+1] %= DIV;
                    }
                }
            }
        }
        long result = 0;
        for(long d: dp[N-1]) result = (result+d)%DIV;
        System.out.println(result%DIV);
    }
}
