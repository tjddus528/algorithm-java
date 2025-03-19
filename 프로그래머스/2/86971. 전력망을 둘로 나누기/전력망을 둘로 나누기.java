import java.util.*;
class Solution {
    static boolean[] visited;
    public int solution(int n, int[][] wires) {
        int answer = n;
        List<List<Integer>> graph;
        for(int i=0; i<n-1; i++) {
            graph = new ArrayList<>();
            for(int ii=0; ii<n+1; ii++) graph.add(new ArrayList<>());
            visited = new boolean[n+1];
            for(int j=0; j<n-1; j++) {
                if(i==j) continue;
                graph.get(wires[j][0]).add(wires[j][1]);
                graph.get(wires[j][1]).add(wires[j][0]);
            }
            for(int node = 1; node <= n; node++) {
                if(!visited[node]) {
                    int cnt = dfs(node, graph);
                    answer = Math.min(Math.abs(n-2*cnt), answer);
                    break;
                }
                
            }
        }
        return answer;
    }
    static int dfs(int node, List<List<Integer>> graph) {
        visited[node] = true;
        int cnt = 1;
        for(int next: graph.get(node)) {
            if(visited[next]) continue;
            cnt += dfs(next, graph);
        }
        return cnt;
    }
}