import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 키 순서
public class Solution {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            int[][] arr = new int[N+1][N+1];
            
            for(int i=0; i<=N; i++) {
                Arrays.fill(arr[i], INF);
                arr[i][i] = 0;
            }
            for(int i=0; i<M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a][b] = 1;
            }

            for(int k=1; k<=N; k++) {
                for(int i=1; i<=N; i++) {
                    for(int j=1; j<=N; j++) {
                        if(arr[i][k] != INF && arr[k][j] != INF)
                            arr[i][j] = Math.min(arr[i][j], arr[i][k]+arr[k][j]);
                    }
                }
            }

            int result = 0;
            for(int i=1; i<=N; i++) {
                int cnt = 0;
                int cntReverse = 0;
                for(int j=1; j<=N; j++) {
                    if(arr[i][j] !=INF && arr[i][j]>0) cnt++;
                    if(arr[j][i] !=INF && arr[i][j]>0) cntReverse++;
                }
                if(cnt + cntReverse == N-1) 
                    result++;
            }

            System.out.println("#"+t+" "+result);

        }
        
    }
}