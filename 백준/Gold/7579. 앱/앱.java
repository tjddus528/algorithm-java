import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 앱
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] V = new int[N+1];
        int[] C = new int[N+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) V[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) C[i] = Integer.parseInt(st.nextToken());

        int maxTime = 0;
        for(int i=1; i<=N; i++) maxTime += C[i];

        // 1차원 DP : 역순으로 채워야 함
        int result = 0;
        int[] D = new int[maxTime+1];
        for(int i=1; i<=N; i++) {
            for(int c=maxTime; c>=C[i]; c--) {
                D[c] = Math.max(D[c], D[c-C[i]] + V[i]);
            }
        }

        for(int c=0; c<=maxTime; c++) {
            if(D[c] >= M) {
                result = c;
                break;
            }
        }

        System.out.println(result);


    }

}
