import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, X;
    static int[] dist;
    static List<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        K = Integer.parseInt(data[2]);
        X = Integer.parseInt(data[3]);
        dist = new int[N+1];
        graph = new ArrayList[N+1];
        for(int i=0; i<=N; i++) dist[i] = Integer.MAX_VALUE;
        for(int i=0; i<=N; i++) graph[i] = new ArrayList();

        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
        }

        boolean[] visited = new boolean[N+1];
        ArrayDeque<Integer> q = new ArrayDeque();
        q.add(X);
        visited[X] = true;
        dist[X] = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next: graph[cur]) {
                if(visited[next]) continue;
                visited[next] = true;
                dist[next] = dist[cur] + 1;
                q.add(next);
            }
        }

        boolean exist = false;
        for(int i=1; i<=N; i++) {
            if (dist[i] == K) {
                System.out.println(i);
                exist = true;
            }
        }
        if (!exist) {
            System.out.println(-1);
        }
    }

}
