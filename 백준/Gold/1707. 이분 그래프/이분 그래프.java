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
  static List<List<Integer>> graph;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    for(int test=0; test<t; test++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int v = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());

      graph = new ArrayList<>();
      for (int i = 0; i <= v; i++)
        graph.add(new ArrayList<>());
      for (int i = 0; i < e; i++) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        graph.get(a).add(b);
        graph.get(b).add(a);
      }
      boolean[] visited = new boolean[v + 1];
      boolean[] check = new boolean[v + 1];
      for(int i=1; i<=v; i++)
        if(!visited[i]) BFS(i, visited, check);
      System.out.println(BinaryGraph(v, check));
    }
  }
  static void BFS(int node, boolean[] visited, boolean[] check) {
    Queue<Integer> q = new LinkedList<>();
    q.add(node);
    visited[node] = true;
    check[node] = true;
    while(!q.isEmpty()) {
      int now = q.poll();
      for(int next: graph.get(now)) {
        check[next] = !check[now];
        if(visited[next]) continue;
        visited[next] = true;
        q.add(next);
      }
    }
  }
  static String BinaryGraph(int v, boolean[] check) {
    for(int i=1; i<=v; i++) {
      for(int next: graph.get(i)) {
        if(check[next]==check[i]) return "NO";
      }
    }
    return "YES";
  }
}
