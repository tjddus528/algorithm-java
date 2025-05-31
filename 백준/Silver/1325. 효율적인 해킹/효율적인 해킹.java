import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<Integer>[] computers;
    static int[] hackCnt;
    static int maxCnt, cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        computers = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            computers[i] = new ArrayList<>();
        }
        hackCnt = new int[N+1];
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            computers[B].add(A);
        }
        for(int i=1; i<=N; i++) {
            cnt = 0;
            dfs(i, new boolean[N+1]);
            hackCnt[i] = cnt;
            maxCnt = Math.max(maxCnt, cnt);
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            if (maxCnt == hackCnt[i]) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }
    static void dfs(int depth, boolean[] hacked) {
        cnt++;
        hacked[depth] = true;
        for(int v: computers[depth]) {
            if(hacked[v]) continue;
            dfs(v, hacked);
        }
    }
}
