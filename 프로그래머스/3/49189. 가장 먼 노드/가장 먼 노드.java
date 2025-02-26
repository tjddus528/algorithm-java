import java.util.*;
class Solution {
    
    
    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>()); // 올바른 리스트 초기화
        }
        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        
        int[] visited = new int[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = 1;
        while(!q.isEmpty()) {
            int now = q.poll();
            // System.out.println(graph[now]);
            for(int next: graph.get(now)) {
                // System.out.println(next);
                if(visited[next] != 0) continue;
                visited[next] = visited[now]+1;
                q.offer(next);
            }
        }
        int max = 0;
        for(int i=1; i<=n; i++) max = Math.max(visited[i], max);
        for(int i=1; i<=n; i++) if(visited[i]==max) answer++;
        
        return answer;
    }
}