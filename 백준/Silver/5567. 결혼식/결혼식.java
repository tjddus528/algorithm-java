import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static List<List<Integer>> friends = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<=N; i++) friends.add(new ArrayList<>());
        for(int i=0; i<M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends.get(a).add(b);
            friends.get(b).add(a);
        }
        int answer = 0;
        if(friends.get(1).isEmpty()) {
            System.out.println(0);
            return;
        }
        boolean[] visited = new boolean[N+1];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1, 0});
        visited[1] = true;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            if(now[1] > 2) break;
            answer++;
            for(int next: friends.get(now[0])) {
                if(visited[next]) continue;
                q.add(new int[]{next, now[1]+1});
                visited[next] = true;
            }
        }
        System.out.println(answer-1);
    }

}
