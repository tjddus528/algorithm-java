import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static List<List<Integer>> graph;
  static final int RED = 1;
  static final int BLUE = -1;
  static int[] colors;
  static boolean checkBipartite;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while(t-->0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int v = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());

      graph = new ArrayList<>();
      for (int i = 0; i <= v; i++)
        graph.add(new ArrayList<>());
      while (e-->0) {
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        graph.get(a).add(b);
        graph.get(b).add(a);
      }
      colors = new int[v+1];
      checkBipartite = true;
      for(int i=1; i<=v; i++) {
        if(!checkBipartite) break;
        if(colors[i]==0) {
          BFS(i, RED);
        }
      }
      if (checkBipartite) {
        System.out.println("YES");
      } else {
        System.out.println("NO");
      }
    }
  }
  static void BFS(int node, int color) {
    Queue<Integer> q = new LinkedList<>();
    q.add(node);
    colors[node] = color;
    while(!q.isEmpty() && checkBipartite) {
      int now = q.poll();
      for(int next: graph.get(now)) {
        // 방문하지 않은 정점이라면
        if(colors[next]==0) {
          q.add(next);
          colors[next] = colors[now] *-1;
        }
        // 서로 인접한 정점의 색이 같은 색이면 이분 그래프가 아니다.
        else if (colors[now]+colors[next] != 0) {
          checkBipartite = false;
          return;
        }
      }
    }
  }
  static void DFS(int node, int color) {
    colors[node] = color;
    for(int next: graph.get(node)) {
      // 시작 정점의 색과 인접한 정점의 색이 같으면 이분 그래프가 아니다.
      if(colors[next] == color) {
        checkBipartite = false;
        return;
      }
      // 시작 정점과 인접한 정점이 방문하지 않은 정점이면 dfs 실행
      if(colors[next]==0) {
        DFS(next, colors[node]*-1);
      }
    }
  }
}