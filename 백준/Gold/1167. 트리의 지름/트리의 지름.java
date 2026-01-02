import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int V;
    static List<List<int[]>> tree = new ArrayList<>();
    static int result = 0;
    static int maxIdx = 0;
    static boolean[] visited;
    static int[] distance;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        for(int i=0; i<=V; i++) tree.add(new ArrayList<>());
        for(int i=0; i<V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            while(st.hasMoreTokens()) {
                int v = Integer.parseInt(st.nextToken());
                if(v == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                tree.get(node).add(new int[]{v, cost});
            }
        }

        visited = new boolean[V+1];
        distance = new int[V+1];
        bfs(1);

        int max = 0;
        for(int i=1; i<=V; i++) {
            if(distance[max] < distance[i]) max = i;
        }

        visited = new boolean[V+1];
        distance = new int[V+1];
        bfs(max);

        Arrays.sort(distance);
        System.out.println(distance[V]);


    }


    static void bfs(int start) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int[] next: tree.get(cur)) {
                int idx = next[0];
                int cost = next[1];
                if(visited[idx]) continue;
                visited[idx] = true;
                q.add(idx);
                distance[idx] = distance[cur] + cost;
            }
        }

    }

}
