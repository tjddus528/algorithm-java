import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  static int m, n;
  static int k;          // 배추의 개수
  static int[][] maps;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static boolean[][] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int test = Integer.parseInt(br.readLine());
    for(int t=0; t<test; t++) {
      int[] data = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
      m = data[0];
      n = data[1];
      k = data[2];
      visited = new boolean[n][m];
      maps = new int[n][m];

      for (int i = 0; i < k; i++) {
        int[] p = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();
        maps[p[1]][p[0]] = 1;
      }

      int count = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (maps[i][j] == 0 || visited[i][j])
            continue;
          count++;
          DFS(j, i);
        }
      }
      System.out.println(count);
    }
  }

  static void DFS(int x, int y) {
    visited[y][x] = true;
    for(int i=0; i<4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if (nx<0 || ny<0 || nx>=m || ny>=n) continue;
      if(maps[ny][nx]==0) continue;
      if(visited[ny][nx]) continue;
      DFS(nx, ny);
    }
  }
}
