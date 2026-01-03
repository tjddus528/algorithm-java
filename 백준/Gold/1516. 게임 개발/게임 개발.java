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
    static boolean[] done;
    static int[] dp;
    static List<List<Integer>> construct = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N+1];
        indegree = new int[N+1];
        done = new boolean[N+1];
        dp = new int[N+1];
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
                dp[next] = Math.max(dp[next], dp[now] + cost[now]);
                indegree[next]--;
                if(indegree[next]==0) q.add(next);
            }
        }
        for(int i=1; i<=N; i++) {
            System.out.println(dp[i]+cost[i]);
        }

    }
}
