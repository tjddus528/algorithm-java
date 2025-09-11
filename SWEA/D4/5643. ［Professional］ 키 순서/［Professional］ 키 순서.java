import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 키 순서
// 시간 : Java의 경우 10초
// 메모리 : 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내
public class Solution {
    static int N, adj[][];
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            // 학생 번호가 1번부터
            adj = new int[N+1][N+1];
            // 메모 안 된 상태로 초기화
            // 첫번재 열, 행에 각각 자신보다 큰 학생수, 작은 학생수 메모
            for(int i=0; i<=N; i++) adj[i][0] = -1;
            for(int i=0; i<M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                adj[a][b] = 1;      // a보다 b가 키가 크다
            }
            // 모든 학생에 대해 자신보다 키가 큰 학생 탐색(이 과정에서 간접관계를 직접관계로 경로 압축)
            int ans = 0;
            for(int i=1; i<=N; i++) {
                if(adj[i][0] == -1) gtDFS(i);
            }

            // 자신보다 작은 학생 카운트
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    adj[0][i] += adj[j][i];
                }
            }
            // 자신보다 키가 큰 학생 + 작은 학생 카운팅
            for(int i=1; i<=N; i++) {
                if(adj[i][0] + adj[0][i] == N-1) ans++;
            }
            System.out.println("#"+t+" "+ans);
        }
    }

    private static int gtDFS(int cur) {
        int ans = -1;
        for(int i=1; i<=N; i++) {
            // cur보다 큰 i라면
            if(adj[cur][i]==1) {

                // i가 탐색을 하지 않았으면 탐색하러 가기
                if(adj[i][0] == -1) gtDFS(i);
                
                // i가 탐색을 이미 한 경우 / 탐색을 끝나고 온 경우
                // i보다 큰 학생이 있다면
                // cur < i < j => cur < j 로 경로 압축해서 관계 표현
                if(adj[i][0] > 0) {
                    for(int j=1; j<=N; j++) {
                        if(adj[i][j] == 1) adj[cur][j] = 1;
                    }
                }
            }
        }
        // 자신보다 큰 학생들 수 카운팅 후 메모
        int cnt = 0;
        for(int i=1; i<= N; i++) {
            if(adj[cur][i]>0) cnt++;
        }
        return adj[cur][0] = cnt;
    }
}
