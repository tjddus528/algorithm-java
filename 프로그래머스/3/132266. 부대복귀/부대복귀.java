import java.util.*;

class Solution {
    static final int MAX_VALUE = 1000_000;
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] cost;
    static int[] answer;
    static boolean[] visited;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int cnt = sources.length;
        cost = new int[n+1];
        answer = new int[cnt];
        visited = new boolean[n+1];
        for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
        for(int[] road: roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        
        Arrays.fill(cost, -1);
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(destination);
        visited[destination] = true;
        cost[destination] = 0;
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int adj: graph.get(cur)) {
                if(visited[adj]) continue;
                visited[adj] = true;
                cost[adj] = cost[cur] + 1;
                q.add(adj);
            }
        }
        for(int i=0; i<cnt; i++)
            answer[i] = cost[sources[i]];
        
        
        return answer;
    }
}