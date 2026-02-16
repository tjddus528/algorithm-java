import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[2]-o2[2]);
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            pq.add(new int[]{A, B, C});
        }

        make(V);
        int answer = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int a = cur[0];
            int b = cur[1];
            int c = cur[2];
            if(union(a, b)) answer += c;
        }
        bw.write(answer+"");
        bw.flush();
        br.close();
        bw.close();

    }

    static void make(int N) {
        parent = new int[N+1];
        for(int i=0; i<=N; i++) parent[i] = i;
    }
    static int find(int node) {
        if(parent[node] == node) return node;
        return parent[node] = find(parent[node]);
    }
    static boolean union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa == pb) return false;
        parent[Math.max(pa, pb)] = Math.min(pa, pb);
        return true;
    }

}
