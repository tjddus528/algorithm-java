import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 평범한 배낭
public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[] D = new int[K+1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            for(int w=K; w>=1; w--) {
                if(w < V) continue;
                D[w] = Math.max(D[w], D[w-V]+C);
            }
        }
        System.out.println(D[K]);
    }

}
