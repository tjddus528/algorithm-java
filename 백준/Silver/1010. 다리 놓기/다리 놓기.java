import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] dp = new int[31][31];
        for(int i=0; i<=30; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
            for(int j=1; j<i; j++) {
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for(int test=0; test<t; test++) {
            String[] data = br.readLine().split(" ");
            int n = Integer.parseInt(data[0]);
            int m = Integer.parseInt(data[1]);
            sb.append(dp[m][n]).append("\n");
        }
        System.out.println(sb);

    }
}
