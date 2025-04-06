import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    int n = Integer.parseInt(data[0]);
    int k = Integer.parseInt(data[1]);
    String str = br.readLine();
    int t = 0;
    for(int i=0; i<str.length(); i++) {
      if(str.charAt(i)=='T') t++;
    }

    int answer = -1;
    Queue<int[]> q = new LinkedList<>();
    boolean[] visited = new boolean[n+1]; // t가 0,1,2,3,4,5개
    q.offer(new int[]{t, 0});
    visited[t] = true;
    while(!q.isEmpty()) {
      int[] now = q.poll();
      int t_cnt = now[0];
      int step = now[1];
      if(t_cnt == n) {
        answer = step;
        break;
      }
      for(int i=0; i<=k; i++) {
        int H = i;
        int T = k-i;
        if(H > n-t_cnt || T > t_cnt) continue;
        if(visited[t_cnt - T + H]) continue;
        visited[t_cnt - T + H] = true;
        q.offer(new int[]{t_cnt - T + H, step+1});
      }
    }
    System.out.println(answer);
  }
}
