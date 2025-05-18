import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] arr;
    static int[] cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        M = Integer.parseInt(data[1]);
        arr = new ArrayList[N+1];
        cnt = new int[N+1];
        for(int i=0; i<N+1; i++) arr[i] = new ArrayList<>();
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            cnt[b]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=1; i<=N; i++){
            if(cnt[i]==0) pq.add(i);
        }
        while(!pq.isEmpty()) {
            int top = pq.poll();
            System.out.print(top+" ");
            for(int v: arr[top]) {
                cnt[v]--;
                if(cnt[v]==0) pq.add(v);
            }
        }

    }
}
