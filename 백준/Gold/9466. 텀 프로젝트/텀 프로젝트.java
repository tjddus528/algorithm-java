import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  static int test, n;
  static int[] arr;
  static boolean[] visited, done;
  static int cnt;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    test = Integer.parseInt(br.readLine());
    while(test-->0) {
      cnt = 0;
      n = Integer.parseInt(br.readLine());
      arr = new int[n+1];
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
        arr[i + 1] = Integer.parseInt(st.nextToken());
      }

      visited = new boolean[n+1];
      done = new boolean[n+1];
      for(int i=1; i<=n; i++)
        dfs(i);
      System.out.println(n - cnt);
    }
  }
  static void dfs(int node) {
    if(visited[node]) {
      done[node] = true;
      cnt++;
    }
    else {
      visited[node] = true;
    }

    int next = arr[node];
    if (!done[next])
      dfs(next);
    visited[node] = false;
    done[node] = true;
  }

}
