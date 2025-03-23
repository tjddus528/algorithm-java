import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
  static int n, m;
  static int[][] maze;
  static int[][] visited;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    n = Integer.parseInt(data[0]);
    m = Integer.parseInt(data[1]);

    maze = new int[n][m];
    for(int i=0; i<n; i++) {
      maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
    }
    visited = new int[n][m];
    bfs(0,0);
    System.out.println(visited[n-1][m-1]);
  }
  static void bfs(int x, int y) {
    visited[x][y] = 1;
    ArrayDeque<Point> q = new ArrayDeque<>();
    q.offer(new Point(x, y));
    while(!q.isEmpty()) {
      Point p = q.poll();
      if(p.x == n-1 && p.y == m-1) return;
      for(int i=0; i<4; i++) {
        Point next = new Point(p.x + dx[i], p.y+dy[i]);
        if(next.x < 0 || next.y < 0 || next.x > n-1 || next.y > m-1) continue;
        if(maze[next.x][next.y] == 0) continue;
        if(visited[next.x][next.y] > 0) continue;
        visited[next.x][next.y] = visited[p.x][p.y] + 1;
        q.offer(next);
      }
    }
  }

  static class Point {
    int x;
    int y;
    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}
