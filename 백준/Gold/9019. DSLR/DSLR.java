import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());
    while(t-->0) {
      String[] data = br.readLine().split(" ");
      int A = Integer.parseInt(data[0]);
      int B = Integer.parseInt(data[1]);
      Map<Integer, String> oper = new HashMap<>();
      boolean[] visited = new boolean[10001];
      BFS(A, B, oper, visited);
      System.out.println(oper.get(B));
    }
  }
  static void BFS(int n, int target, Map<Integer, String> oper, boolean[] visited) {
    ArrayDeque<Integer> q = new ArrayDeque<>();
    q.offer(n);
    oper.put(n, "");
    visited[n] = true;
    while(!q.isEmpty()) {
      int now = q.poll();
      if(now == target)
        return;

      int D = (now*2)%10000;
      int S = (now==0)?9999 : now-1;
      int L = (now%1000)*10 + now/1000;
      int R = (now%10)*1000 + now/10;

      if(!visited[D]) {
        oper.put(D, oper.get(now)+"D");
        visited[D] = true;
        q.offer(D);
      }
      if(!visited[S]) {
        oper.put(S, oper.get(now)+"S");
        visited[S] = true;
        q.offer(S);
      }
      if(!visited[L]) {
        oper.put(L, oper.get(now)+"L");
        visited[L] = true;
        q.offer(L);
      }
      if(!visited[R]) {
        oper.put(R, oper.get(now)+"R");
        visited[R] = true;
        q.offer(R);
      }
    }
  }
}
