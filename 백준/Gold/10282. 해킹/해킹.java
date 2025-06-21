import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Node implements Comparable<Node> {
        int next;
        int time;
        Node(int nxt, int t) {
            this.next = nxt;
            this.time = t;
        }
        @Override
        public int compareTo(Node node) {
            return this.time - node.time;
        }
    }
    static int test;
    static int N, D, C;
    static List<List<Node>> graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test = Integer.parseInt(br.readLine());
        while(test --> 0) {
            String[] data = br.readLine().split(" ");
            N = Integer.parseInt(data[0]);
            D = Integer.parseInt(data[1]);
            C = Integer.parseInt(data[2]);

            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++)
                graph.add(new ArrayList<>());
            for (int i = 0; i < D; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int b = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                graph.get(a).add(new Node(b, t));
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            boolean[] visited = new boolean[N + 1];
            pq.add(new Node(C, 0));
            int cnt = 0;
            int totalTime = 0;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                if (visited[cur.next])
                    continue;
                visited[cur.next] = true;
                cnt++;
                totalTime = cur.time;
                for (Node nd : graph.get(cur.next)) {
                    if (visited[nd.next])
                        continue;
                    pq.add(new Node(nd.next, cur.time + nd.time));
                }
            }
            System.out.println(cnt + " " + totalTime);
        }
    }
}
