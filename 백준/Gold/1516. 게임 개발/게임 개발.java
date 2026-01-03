import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int N;
    static int[] cost;
    static int[] indegree;
    static int[] prev;
    static List<List<Integer>> construct = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N+1];            // cost : 건물 건설 비용 (해당 건물을 짓기 위해 필요한 총 건설 비용)
        prev = new int[N+1];            // prev : 해당 건물을 짓기 전 필요한 이전 건물 건설 비용
        indegree = new int[N+1];        // indegree : 진입 차수 (해당 건물을 짓기 전 지어야 하는 건물 수)
        for(int i=0; i<=N; i++) construct.add(new ArrayList<>());
        for(int i=1; i<=N; i++) {
            String[] data = br.readLine().split(" ");
            cost[i] = Integer.parseInt(data[0]);
            for(int j=1; j<data.length-1; j++) {
                construct.get(Integer.parseInt(data[j])).add(i);
                indegree[i]++;
            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i=1; i<=N; i++) {
            if(indegree[i]==0) q.add(i);
        }

        while(!q.isEmpty()) {
            int now = q.poll();
            for(int next: construct.get(now)) {
                prev[next] = Math.max(prev[next], prev[now] + cost[now]);
                indegree[next]--;
                if(indegree[next]==0) q.add(next);
            }
        }

        for(int i=1; i<=N; i++) {
            System.out.println(cost[i] + prev[i]);
        }
    }
}
