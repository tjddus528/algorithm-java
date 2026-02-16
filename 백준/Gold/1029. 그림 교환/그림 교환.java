import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] visited = new boolean[16];
    static int[][] price = new int[16][16];
    static int N;
    static int answer;
    static int[][][] dp = new int[16][1<<16][10];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<N; j++){
                price[i][j] = input.charAt(j) - '0';
            }
        }

        dfs(0, 0, 1, 1);
        System.out.println(answer);
    }
    static void dfs(int cur, int p, int cnt, int path) {
        if(path == (1<<N)) {    // 모든 아티스트가 소유했다면
            answer = N;
            return;
        }
        if(dp[cur][path][p] >= cnt) return; // 현재 아티스트가 소유했던 경우와 현재까지의 경로 이미 있었고, 그때의 소유자 수가 더 최대일 때

        answer = Math.max(answer, cnt);
        dp[cur][path][p] = cnt;

        for(int i=0; i<N; i++) {
            if((path & (1<<i)) != 0) continue;
            if(price[cur][i] < p) continue;
            dfs(i, price[cur][i], cnt+1, path | (1<<i));
        }
    }
}