import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-- >0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[] distance = new int[N+1];
            for(int i=0;i<=N;i++) distance[i] = Integer.MAX_VALUE;
            distance[C] = 0;

            List<Node>[] graph = new ArrayList[N+1];
            for(int i=1;i<=N;i++) graph[i] = new ArrayList<>();
            for(int i=0;i<D;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph[b].add(new Node(a,s));
            }

            PriorityQueue<Node> queue = new PriorityQueue<>();
            boolean[] check = new boolean[N+1];
            queue.add(new Node(C,0));

            while(!queue.isEmpty()){
                Node curr = queue.poll();
                if(check[curr.node]) continue;
                check[curr.node] = true;

                for(Node next : graph[curr.node]){
                    if(distance[next.node] > distance[curr.node] + next.edge){
                        distance[next.node] = distance[curr.node] + next.edge;
                        queue.add(new Node(next.node, distance[next.node]));
                    }
                }
            }

            int cnt = 0;
            int time = 0;
            for(int i=1;i<=N;i++){
                if(distance[i] != Integer.MAX_VALUE){
                    cnt++;
                    time = Math.max(time,distance[i]);
                }
            }
            sb.append(cnt).append(" ").append(time).append("\n");
        }

        System.out.println(sb);
    }

    static class Node implements Comparable<Node>{
        int node;
        int edge;

        Node(int node, int edge){
            this.node = node;
            this.edge = edge;
        }

        @Override
        public int compareTo(Node o) {
            return this.edge - o.edge;
        }
    }
}