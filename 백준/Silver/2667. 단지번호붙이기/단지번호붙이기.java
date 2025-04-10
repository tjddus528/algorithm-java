import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
  static boolean[][] visited;
  static int[][] matrix;
  static int n;
  static int cnt;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    matrix = new int[n][n];
    for(int i=0; i<n; i++) {
      matrix[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
    }

    List<Integer> result = new ArrayList<>();
    visited = new boolean[n][n];
    for(int i=0; i<n; i++){
      for(int j=0; j<n; j++) {
        if(visited[i][j]) continue;
        if(matrix[i][j]==0) continue;
        cnt = 0;
        dfs(i, j);
        result.add(cnt);
      }
    }
    Collections.sort(result);
    System.out.println(result.size());
    for (int res : result) {
      System.out.println(res);
    }
  }
  static void dfs(int x, int y) {
    cnt++;
    visited[x][y] = true;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    for(int i=0; i<4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];
      if(nx<0 || ny<0 || nx>n-1 || ny>n-1) continue;
      if(visited[nx][ny]) continue;
      if(matrix[nx][ny]==0) continue;
      dfs(nx, ny);
    }
  }
}
