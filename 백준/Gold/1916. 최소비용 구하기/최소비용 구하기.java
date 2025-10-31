import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<List<int[]>> graph = new ArrayList<>();
        for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
        StringTokenizer st;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(a).add(new int[]{b, cost});
        }
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 비용 오름차순 정렬된 PQ
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        pq.add(new int[]{A, 0});
        int[] distance = new int[N+1];
        for(int i=0; i<N+1; i++) distance[i] = INF;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];
            if(node == B) {
                System.out.println(cost);
                break;
            }
            for(int[] next: graph.get(node)) {
                int nnode = next[0];
                int ncost = next[1];
                if(cost+ncost < distance[nnode]) {
                    distance[nnode] = cost + ncost;
                    pq.add(new int[]{nnode, distance[nnode]});
                }
            }
        }


    }

}
