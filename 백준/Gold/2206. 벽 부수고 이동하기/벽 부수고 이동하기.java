import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
  static int[][] matrix;
  static int n, m;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static int result = -1;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] data = br.readLine().split(" ");
    n = Integer.parseInt(data[0]);
    m = Integer.parseInt(data[1]);

    matrix = new int[n][m];
    for(int i=0; i<n; i++) {
      matrix[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
    }
    // 최단거리 저장 배열 초기화 (MAX_NUM)
    boolean[][][] visited = new boolean[n][m][2];
    bfs(0, 0, visited);
    System.out.println(result);

  }
  static void bfs(int x, int y, boolean[][][] visited) {
    ArrayDeque<int[]> q = new ArrayDeque<>();
    visited[x][y][0] = true;
    // x, y, 벽을 부수지 않았을 때 거리, 벽을 부순 적이 있는지
    q.offer(new int[]{x, y, 1, 0});
    while(!q.isEmpty()) {
      int[] now = q.poll();
      if(now[0]==n-1 && now[1]==m-1) {
        result = now[2];
        return;
      }
      for(int i=0; i<4; i++) {
        int nx = now[0] + dx[i];
        int ny = now[1] + dy[i];
        if(nx<0 || ny<0 || nx>n-1 || ny>m-1) continue;
        // 벽이면
        if(matrix[nx][ny]==1) {
          // 벽을 부순 적이 없다면 벽을 부순다
          if(now[3]==0) {
            q.add(new int[]{nx, ny, now[2]+1, 1});
          }
        }
        // 벽이 아니면
        else {
          // 이전에 벽을 부순 적이 없다면 부순다
          if(now[3]==0 && !visited[nx][ny][0]) {
            q.add(new int[]{nx, ny, now[2]+1, 0});
            visited[nx][ny][0] = true;
          }
          // 이전에 벽을 부순 적이 있다면
          else if (now[3] ==1 && !visited[nx][ny][1]){
            q.add(new int[]{nx, ny, now[2]+1, 1});
            visited[nx][ny][1] = true;
          }
        }
      }
    }
  }
}
