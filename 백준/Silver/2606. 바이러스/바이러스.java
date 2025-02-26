import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  static List<List<Integer>> graph = new ArrayList<>();
  static boolean[] visited;
  static int count = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int m = Integer.parseInt(br.readLine());
    for(int i=0; i<=n; i++) graph.add(new ArrayList<>());
    for(int i=0; i<m; i++) {
      int[] e = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
      graph.get(e[0]).add(e[1]);
      graph.get(e[1]).add(e[0]);
    }

    int start = 1;
    visited = new boolean[n+1];
    DFS(start);
    System.out.println(count-1);
  }

  static void DFS(int start) {
    visited[start] = true;
    count++;
    for(int next: graph.get(start)) {
      if(visited[next]) continue;
      DFS(next);
    }
  }

}
