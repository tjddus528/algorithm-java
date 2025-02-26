import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
  static int[] dy = {0, 0, -1, 1, -1, 1, 1, -1};
  static int[][] maps;
  static boolean[][] visited;
  static int w, h;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while(true) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      w = Integer.parseInt(st.nextToken());
      h = Integer.parseInt(st.nextToken());
      if (w == 0 && h == 0)
        return;

      maps = new int[h][w];
      visited = new boolean[h][w];
      for (int i = 0; i < h; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < w; j++) {
          maps[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      int count = 0;
      for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
          if (visited[i][j] || maps[i][j] == 0)
            continue;
          count++;
          BFS(i, j);
        }
      }
      System.out.println(count);
    }


  }
  static void BFS(int x, int y) {
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{x, y});
    visited[x][y] = true;
    while(!q.isEmpty()) {
      int[] now = q.poll();
      for(int i=0; i<8; i++) {
        int nx = now[0] + dx[i];
        int ny = now[1] + dy[i];
        if(nx<0 || ny<0 || nx>=h || ny>=w) continue;
        if(visited[nx][ny]) continue;
        if(maps[nx][ny]==0) continue;
        visited[nx][ny] = true;
        q.offer(new int[]{nx, ny});
      }
    }
  }

}
