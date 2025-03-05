import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    List<List<Integer>> graph = new ArrayList<>();
    for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
    for(int i=0; i<n-1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    int[] visited = new int[n+1];
    Queue<Integer> q = new LinkedList<>();
    q.offer(1);
    visited[1] = 1;
    while(!q.isEmpty()) {
      int now = q.poll();
      for(int next: graph.get(now)) {
        if(visited[next]>0) continue;
        visited[next] = visited[now]+1;
        q.offer(next);
      }
    }
    for(int i=2; i<=n; i++) {
      for(int v: graph.get(i)) {
        if (visited[v] < visited[i]) {
          System.out.println(v);
        }
      }
    }
  }
}
