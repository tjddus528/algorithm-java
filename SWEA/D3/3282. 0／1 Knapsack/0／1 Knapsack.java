import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 0/1 Knapsack
public class Solution {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int[] v = new int[N+1];
            int[] c = new int[N+1];
            for(int i=1; i<=N; i++) {
                st = new StringTokenizer(br.readLine());
                v[i] = Integer.parseInt(st.nextToken());
                c[i] = Integer.parseInt(st.nextToken());
            }

            // 1차원 DP
            int[] dp2 = new int[K+1];
            for(int i=1; i<=N; i++) {
                for(int w=K; w>=1; w--) {
                    if(w < v[i]) continue;
                    dp2[w] = Math.max(dp2[w], dp2[w-v[i]] + c[i]);
                }
            }
            sb.append("#").append(t).append(" ").append(dp2[K]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
