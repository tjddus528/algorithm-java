import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 87654321;
    static int N, E;
    static List<List<int[]>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        for(int i=0; i<=N; i++) graph.add(new ArrayList<>());

        int[][] dist = new int[N+1][N+1];
        for(int i=0; i<=N; i++) {
            for(int j=0; j<=N; j++) {
                dist[i][j] = INF;
                if(i==j) dist[i][j] = 0;
            }
        }
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
            dist[a][b] = c;
            dist[b][a] = c;
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        for(int k=1; k<=N; k++) {
            for (int i = 1; i <= N; i++) {
                if(i==k) continue;
                for (int j = 1; j <= N; j++) {
                    if(i==j || j==k) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int res1 = dist[1][v1] + dist[v1][v2] + dist[v2][N];
        int res2 = dist[1][v2] + dist[v2][v1] + dist[v1][N];
        int answer = Math.min(res1, res2);
        if (answer >= INF)
            System.out.println(-1);
        else
            System.out.println(answer);

    }

}

