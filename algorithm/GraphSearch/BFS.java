package algorithm.GraphSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
  static List<List<Integer>> graph = new ArrayList<>();
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int n = data[0];  // 정점 개수
    int m = data[1];  // 간선 개수
    int v = data[2];  // 처음 방문할 정점

    // graph 초기화
    for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
    for(int i=0; i<m; i++) {
      int[] e = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      graph.get(e[0]).add(e[1]);
      graph.get(e[1]).add(e[0]);
    }

    // 정점 정렬 (인접한 정점이 여럿일 경우 숫자가 작은 정점부터 방문한다)
    for (int i = 1; i <= n; i++) {
      Collections.sort(graph.get(i));
    }

    // BFS
    visited = new boolean[n+1];
    BFS(v);
  }
  static void BFS(int start) {
    Queue<Integer> q = new LinkedList<>();
    q.offer(start);
    visited[start] = true;
    while (!q.isEmpty()) {
      int now = q.poll();
      System.out.print(now + " ");
      for (int next : graph.get(now)) {
        if (visited[next])
          continue;
        visited[next] = true;
        q.offer(next);
      }
    }
  }

}
