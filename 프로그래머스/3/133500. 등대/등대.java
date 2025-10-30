import java.util.*;
class Solution {
    static List<List<Integer>> graph = new ArrayList<>();
    static int[] parent;
    static boolean[] lights;
    static boolean[] checked;
    static int answer = 0;
    public int solution(int n, int[][] lighthouse) {
        parent = new int[n+1];
        lights = new boolean[n+1];
        checked = new boolean[n+1];
        for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
        for(int[] light: lighthouse) {
            graph.get(light[0]).add(light[1]);
            graph.get(light[1]).add(light[0]);
        }
        
        // 부모 노드를 확인하는 BFS
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n+1];
        q.add(1);
        visited[1] = true;
        while(!q.isEmpty()) {
            int cur = q.poll();
            for(int next: graph.get(cur)) {
                if(visited[next]) continue;
                visited[next] = true;
                parent[next] = cur;
                q.offer(next);
            }
        }
        
        checked[1] = true;
        dfs(1);
        return answer;
    }
    static void dfs(int node) {
        
        for(int child: graph.get(node)) {
            if(checked[child]) continue;
            checked[child] = true;
            dfs(child);
        }
        
        // 자식 노드가 불이 다 켜져있으면 현재 노드는 키지 않는다.
        int cnt = 0;
        for(int child: graph.get(node)) {
            if(lights[child]) cnt++;
        }
        if(cnt == graph.get(node).size()) return;
        
        // 자신이 불이 켜져있으면 넘어간다.
        if(lights[node]) return;
        
        // 리프 노드인데 부모 노드가 꺼져있으면, 부모 노드를 킨다.
        if(!lights[parent[node]]) {
            answer++;
            lights[parent[node]] = true;
        }
    }
}