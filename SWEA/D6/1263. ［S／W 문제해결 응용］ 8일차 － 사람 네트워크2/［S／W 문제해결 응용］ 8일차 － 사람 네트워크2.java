import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사람 네트워크
public class Solution {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[][] D = new int[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    if(v==0) D[i][j] = INF;
                    else D[i][j] = v;
                }
                D[i][i] = 0;
            }

            for(int k=0; k<N; k++) {
                for(int i=0; i<N; i++) {
                    for(int j=0; j<N; j++) {
                        if(D[i][k] == INF || D[k][j] == INF) continue;
                        D[i][j] = Math.min(D[i][j], D[i][k]+D[k][j]);
                    }
                }
            }
            
            int min = Integer.MAX_VALUE;
            for(int i=0; i<N; i++) {
                int total = 0;
                for(int j=0; j<N; j++) {
                    total += D[i][j];
                }
                min = Math.min(min, total);
            }

            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb.toString());
    }   

}
