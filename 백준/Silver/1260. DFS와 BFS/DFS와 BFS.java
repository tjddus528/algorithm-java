import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
  static List<List<Integer>> graph = new ArrayList<>();
  static boolean[] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int n = data[0];
    int m = data[1];
    int v = data[2];

    for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
    for(int i=0; i<m; i++) {
      int[] e = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      graph.get(e[0]).add(e[1]);
      graph.get(e[1]).add(e[0]);
    }
    for (int i = 1; i <= n; i++) {
      Collections.sort(graph.get(i));
    }

    // DFS
    visited = new boolean[n+1];
    DFS(v);
    System.out.println();
    
    // BFS
    visited = new boolean[n+1];
    BFS(v);
  }
  static void DFS(int start) {
    System.out.print(start+" ");
    visited[start] = true;
    for(int next: graph.get(start)) {
      if(visited[next]) continue;
      DFS(next);
    }
  }
  static void BFS(int start) {
    Queue<Integer> q = new LinkedList<>();
    q.offer(start);
    visited[start] = true;
    while(!q.isEmpty()) {
      int now = q.poll();
      System.out.print(now + " ");
      for(int next: graph.get(now)) {
        if(visited[next]) continue;
        visited[next] = true;
        q.offer(next);
      }
    }
  }
}
