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

        /**
         * 메모리를 "col"로 설정했을 때 => X
         * M 메모리를 채울 수 있는 최소 비용을 고르는 것이기 때문에 메모리를 col로 설정하면 안 됨
         * 메모리 제한이 10^7까지 올 수 있기 때문에 N*M <= 10^9 으로 시간초과가 나기도 함
         * 
         */
        
        // int[][] D = new int[N+1][M+1];
        // for(int i=1; i<=N; i++) {
        //     for(int j=M; j>=1; j--) {
        //         if(j<V[i]) continue;
        //         D[i][j] = Math.max(D[i-1][j], D[i-1][j-V[i]] + C[i]);
        //     }
        // }

        // for(int i=0; i<=N; i++) {
        //     for(int j=0; j<=M; j++) {
        //         System.out.print(D[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        // System.out.println(D[N][M]);

        /**
         * 실행비용을 "col"로 설정했을 때
         * 각 비용은 최대 100까지 올 수 있기 때문에 N*C(100)을 해도 최대 10^4 
         * 최소 비용을 구해야 하기 때문에 냅색 동적테이블을 구해서 마지막행/마지막열의 값을 구하는 것이 아님 (메모리 값이 들어가 있음, 해당 비용을 사용했을 때 가장 많은 메모리를 비울 수 있는 경우)
         * 따라서 메모리 M을 만족하는 최소 비용을 찾기 위해서는 냅색 동적테이블에서 처음으로 메모리가 M을 초과하기 시작하는 비용을 출력하면 된다
         * 
         * 냅색은 1차원 DP, 2차원 DP로 모두 풀 수 있다.
         * 1) 첫번째 방법은 2차원 DP
         * - 이전 행의 데이터를 활용해야 하고,
         * - 해당 열을 선택하는 경우 / 선택하지 않는 경우가 있다
         * - 해당 열을 선택하는 경우에는 D[i-1][W-C[i]] + V[i] (이전 행에서 해당 열의 비용을 제외한 남은 공간에 현재 열의 비용을 추가한 값)
         * - 그리고 선택하지 않는 경우에는 이전 행의 같은 열의 값을 그대로 가져와야 한다 (**중요** : 이걸 간과해서 틀렸음)
         * 
         * 2) 두번재 방법은 1차원 DP
         * - 비용을 앞에서부터 순회하면 이전 데이터가 오염되기 때문에 
         * - **뒤에서부터 순회해서 깨끗한 이전 데이터를 같은 행에서 활용한다**
         * 
         * 마지막에는 동일하게 처음으로 dp값이 M을 초과하는 비용이 최소이므로, 그 비용을 출력
         */
        // 
        int maxTime = 0;
        for(int i=1; i<=N; i++) maxTime += C[i];

        // 1)
        // 2차원 DP : 안 고르는 경우를 채워야 함
        // int result = 0;
        // int[][] D = new int[N+1][maxTime+1];
        // for(int i=1; i<=N; i++) {
        //     // for(int j=1; j<=maxTime; j++) {
        //     for(int j=0; j<= maxTime; j++) {
        //         D[i][j] = D[i - 1][j]; // 안 고르는 경우
        //         System.out.println("j:"+ j +" -> "+V[i] +", "+C[i]);
        //         if(j >= C[i])
        //             D[i][j] = Math.max(D[i-1][j], D[i-1][j-C[i]] + V[i]);
        //     }
        // }

        // for(int j=1; j<=maxTime; j++) {
        //     if(D[N][j] >= M) {
        //         result = j;
        //         break;
        //     }
        // }
        // System.out.println(result);

        // 2)
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
